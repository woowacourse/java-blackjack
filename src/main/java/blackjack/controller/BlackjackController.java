package blackjack.controller;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.Profit;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        Participants participants = makeParticipants();
        Deck deck = new Deck();

        bettingMoney(participants.getPlayers());
        setInitCards(deck, participants);

        turnOfPlayers(participants.getPlayers(), deck);
        turnOfDealer(participants.getDealer(), deck);

        finishGame(participants);
    }

    private Participants makeParticipants() {
        List<String> playersName = inputView.receivePlayersName();
        Players players = Players.from(playersName);

        return new Participants(new Dealer(), players);
    }

    private void setInitCards(final Deck deck, final Participants participants) {
        participants.receiveCards(deck, NUMBER_OF_SETTING_CARDS);
        printParticipantsInitCards(participants);
    }

    private void printParticipantsInitCards(final Participants participants) {
        outputView.printDistributeCardsMessage(getPlayerNames(participants.getPlayers()));
        outputView.printDealerInitCards(getDealersCards(participants.getDealer()));
        outputView.printPlayersInitCards(getPlayersCards(participants.getPlayers()));
    }

    private void bettingMoney(final List<Player> players) {
        for (Player player : players) {
            int receiveBettingMoney = inputView.receiveBettingMoney(player.getName());
            player.betting(receiveBettingMoney);
        }
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
            outputView.printCurrentCards(player.getName(), getCurrentCards(player.getHand()));
            return false;
        }

        player.receiveCard(deck.drawCard());
        outputView.printCurrentCards(player.getName(), getCurrentCards(player.getHand()));
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
            outputView.printDealerDrawOneMoreCard();
        }
    }

    private void finishGame(final Participants participants) {
        List<Player> players = participants.getPlayers();
        List<Score> scores = getPlayersScore(players);
        Dealer dealer = participants.getDealer();

        outputView.printDealerFinalCards(getCurrentCards(dealer.getHand()), dealer.calculateTotalScore());
        outputView.printPlayerFinalCards(getPlayersCards(players), scores);

        showProfit(participants);
    }

    private void showProfit(final Participants participants) {
        Profit profit = new Profit(participants);
        Map<Player, Money> playersProfit = profit.makePlayersProfit();
        Money dealerProfit = profit.getDealerProfit(playersProfit);
        Map<String, Integer> participantsProfit = getProfit(playersProfit, dealerProfit, participants.getDealer());
        outputView.printProfit(participantsProfit);
    }

    private List<String> getPlayerNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    private List<String> getDealersCards(final Dealer dealer) {
        return dealer.showInitCards().stream()
                .map(Card::getCardInfo)
                .collect(Collectors.toList());
    }

    private Map<String, List<String>> getPlayersCards(final List<Player> players) {
        Map<String, List<String>> playersCards = new HashMap<>();

        for (Player player : players) {
            playersCards.put(player.getName(),
                    player.showInitCards().stream()
                            .map(Card::getCardInfo)
                            .collect(Collectors.toList()));
        }

        return playersCards;
    }

    private List<Score> getPlayersScore(final List<Player> players) {
        return players.stream()
                .map(Player::calculateTotalScore)
                .collect(Collectors.toList());
    }

    private List<String> getCurrentCards(final List<Card> cards) {
        return cards.stream()
                .map(Card::getCardInfo)
                .collect(Collectors.toList());
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
