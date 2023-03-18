package blackjack.controller;

import blackjack.domain.participant.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.Profit;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class BlackjackController {

    private static final int NUMBER_OF_SETTING_CARDS = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Players players = makePlayers();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        setInitCards(deck, dealer, players);
        turnOfPlayers(players.getPlayers(), deck);
        turnOfDealer(dealer, deck);
        finishGame(dealer, players);
    }

    private Players makePlayers() {
        List<String> playersName = inputView.receivePlayersName();
        Map<String, Integer> players = new LinkedHashMap<>();

        for (String playerName : playersName) {
            int receiveBettingMoney = inputView.receiveBettingMoney(playerName);
            players.put(playerName, receiveBettingMoney);
        }

        return Players.from(players);
    }

    private void setInitCards(final Deck deck, final Dealer dealer, final Players players) {
        for (Player player : players.getPlayers()) {
            distributeInitCards(deck, player);
        }
        distributeInitCards(deck, dealer);
        printParticipantsInitCards(dealer, players);
    }

    private void distributeInitCards(final Deck deck, final Participant participant) {
        for (int i = 0; i < NUMBER_OF_SETTING_CARDS; i++) {
            Card drawnCard = deck.drawCard();
            participant.receiveCard(drawnCard);
        }
    }

    private void printParticipantsInitCards(final Dealer dealer, final Players players) {
        List<String> playerNames = getPlayerNames(players.getPlayers());
        outputView.printDistributeCardsMessage(dealer.getName(), playerNames);

        Map<String, List<String>> participantsInitCards = getParticipantsInitCards(dealer, players.getPlayers());
        outputView.printParticipantsInitCards(participantsInitCards);
    }

    private void turnOfPlayers(final List<Player> players, final Deck deck) {
        for (Player player : players) {
            play(player, deck);
        }
    }

    private void play(final Player player, final Deck deck) {
        boolean proceed = true;

        while (proceed && player.canDraw()) {
            proceed = ask(player, deck);
            printBustMessage(player);
        }
    }

    private boolean ask(final Player player, final Deck deck) {
        Boolean answer = inputView.askReceiveMoreCard(player.getName());

        if (!answer) {
            outputView.printCurrentCards(player.getName(), getCards(player.getHand()));
            return false;
        }

        player.receiveCard(deck.drawCard());
        outputView.printCurrentCards(player.getName(), getCards(player.getHand()));
        return true;
    }

    private void printBustMessage(final Player player) {
        if (!player.canDraw()) {
            outputView.printBustMessage();
        }
    }

    private void turnOfDealer(final Dealer dealer, final Deck deck) {
        while (dealer.canDraw()) {
            dealer.receiveCard(deck.drawCard());
            outputView.printDealerDrawOneMoreCard(dealer.getName());
        }
    }

    private void finishGame(final Dealer dealer, final Players players) {
        List<Integer> scores = getParticipantScores(dealer, players.getPlayers());
        Map<String, List<String>> participantsFinalCards = getParticipantsFinalCards(dealer, players.getPlayers());

        outputView.printParticipantsScore(participantsFinalCards, scores);

        showProfit(dealer, players);
    }

    private void showProfit(final Dealer dealer, final Players players) {
        Profit profit = new Profit();
        Map<Player, Money> playersProfit = profit.makePlayersProfit(dealer, players);
        Money dealerProfit = profit.getDealerProfit(playersProfit);
        Map<String, Integer> participantsProfit = getProfit(playersProfit, dealerProfit, dealer);

        outputView.printProfit(participantsProfit);
    }

    private List<String> getPlayerNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    private Map<String, List<String>> getParticipantsInitCards(final Dealer dealer, final List<Player> players) {
        Map<String, List<String>> participantsInitCards = new LinkedHashMap<>() {{
            put(dealer.getName(), getCards(dealer.showInitCards()));
        }};

        for (Player player : players) {
            participantsInitCards.put(player.getName(), getCards(player.showInitCards()));
        }

        return participantsInitCards;
    }

    private Map<String, List<String>> getParticipantsFinalCards(final Dealer dealer, final List<Player> players) {
        Map<String, List<String>> participantsFinalCards = new LinkedHashMap<>() {{
            put(dealer.getName(), getCards(dealer.getHand()));
        }};

        for (Player player : players) {
            participantsFinalCards.put(player.getName(), getCards(player.getHand()));
        }

        return participantsFinalCards;
    }

    private List<String> getCards(final List<Card> cards) {
        return cards.stream()
                .map(this::makeCardInfo)
                .collect(Collectors.toList());
    }

    private List<Integer> getParticipantScores(final Dealer dealer, final List<Player> players) {
        List<Integer> scores = new LinkedList<>() {{
            add(dealer.calculateTotalScore().getValue());
        }};

        for (Player player : players) {
            scores.add(player.calculateTotalScore().getValue());
        }

        return scores;
    }

    private String makeCardInfo(Card card) {
        return card.getCardNumber().getState() + card.getCardPattern().getName();
    }

    private Map<String, Integer> getProfit(final Map<Player, Money> playersProfit, final Money dealerProfit,
                                           final Dealer dealer) {
        Map<String, Integer> profit = new LinkedHashMap<>();

        profit.put(dealer.getName(), dealerProfit.getMoney());
        for (Player player : playersProfit.keySet()) {
            profit.put(player.getName(), playersProfit.get(player).getMoney());
        }

        return profit;
    }
}
