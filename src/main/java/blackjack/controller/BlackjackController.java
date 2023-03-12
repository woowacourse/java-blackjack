package blackjack.controller;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.BlackjackParticipant;
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
        Players players = makePlayers();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        settingGame(players, dealer, deck);
        turnOfPlayers(players.getPlayers(), deck);
        turnOfDealer(dealer, deck);
        finishGame(dealer, players);
    }

    private Players makePlayers() {
        List<String> playersName = inputView.receivePlayersName();
        return Players.from(playersName);
    }

    private void settingGame(final Players players,final Dealer dealer,final Deck deck) {
        bettingMoney(players);
        setInitCards(deck, dealer, players);
    }

    private void setInitCards(final Deck deck, final Dealer dealer, final Players players) {
        for (Player player : players.getPlayers()) {
            distributeInitCards(deck, player);
        }
        distributeInitCards(deck, dealer);
        printParticipantsInitCards(dealer, players);
    }

    private void distributeInitCards(final Deck deck, final BlackjackParticipant participant) {
        for (int i = 0; i < NUMBER_OF_SETTING_CARDS; i++) {
            Card drawnCard = deck.drawCard();
            participant.receiveCard(drawnCard);
        }
    }

    private void printParticipantsInitCards(final Dealer dealer, final Players players) {
        outputView.printDistributeCardsMessage(getPlayerNames(players.getPlayers()));
        outputView.printDealerInitCards(getDealersCards(dealer));
        outputView.printPlayersInitCards(getPlayersCards(players.getPlayers()));
    }

    private void bettingMoney(final Players players) {
        for (Player player : players.getPlayers()) {
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

    private void finishGame(final Dealer dealer, final Players players) {
        List<Score> scores = getPlayersScore(players.getPlayers());

        outputView.printDealerFinalCards(getCurrentCards(dealer.getHand()), dealer.calculateTotalScore());
        outputView.printPlayerFinalCards(getPlayersCards(players.getPlayers()), scores);

        showProfit(dealer, players);
    }

    private void showProfit(final Dealer dealer, final Players players) {
        Profit profit = new Profit(dealer, players);
        Map<Player, Money> playersProfit = profit.makePlayersProfit();
        Money dealerProfit = profit.getDealerProfit(playersProfit);
        Map<String, Integer> participantsProfit = getProfit(playersProfit, dealerProfit, dealer);
        outputView.printProfit(participantsProfit);
    }

    private List<String> getPlayerNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    private List<String> getDealersCards(final Dealer dealer) {
        return dealer.showInitCards().stream()
                .map(card -> makeCardInfo(card))
                .collect(Collectors.toList());
    }

    private Map<String, List<String>> getPlayersCards(final List<Player> players) {
        Map<String, List<String>> playersCards = new HashMap<>();

        for (Player player : players) {
            playersCards.put(player.getName(),
                    player.showInitCards().stream()
                            .map(card -> makeCardInfo(card))
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
                .map(card -> makeCardInfo(card))
                .collect(Collectors.toList());
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
