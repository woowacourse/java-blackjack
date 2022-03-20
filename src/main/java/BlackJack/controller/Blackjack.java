package blackJack.controller;

import blackJack.domain.Card.Deck;
import blackJack.domain.Result.Result;
import blackJack.domain.User.Dealer;
import blackJack.domain.User.Player;
import blackJack.domain.User.Players;
import blackJack.domain.User.User;
import blackJack.view.InputView;
import blackJack.view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static blackJack.domain.Card.Deck.initDeck;

public class Blackjack {

    private static final int HAND_OUT_COUNT = 2;

    public void run() {
        final Dealer dealer = new Dealer();
        final Deck deck = new Deck(initDeck());
        List<String> playerNames = InputView.inputPlayerNames();
        Map<String, Integer> bettingMoneys = getBettingMoneys(playerNames);
        final Players players = new Players(playerNames, bettingMoneys);

        handOutInitCard(dealer, players, deck);

        OutputView.printDrawMessage(playerNames);
        OutputView.printTotalUserCards(dealer, players);

        if (!dealer.isBlackJack()) {
            playGame(dealer, players, deck);
        }

        Map<Player, Integer> playerResult = Result.makePlayerResult(dealer, players);
        int dealerResult = Result.calculateDealerProfit(playerResult);

        printProfit(dealer, playerResult, dealerResult);
    }

    private void printProfit(Dealer dealer, Map<Player, Integer> playerResults, int dealerResult) {
        OutputView.printFinalResult(
                dealer.getName(),
                playerResults,
                dealerResult
        );
    }

    private Map<String, Integer> getBettingMoneys(List<String> playerNames) {
        Map<String, Integer> bettingMoneys = new HashMap<>();
        for (String playerName : playerNames) {
            bettingMoneys.put(playerName, InputView.inputBettingMoney(playerName));
        }
        return bettingMoneys;
    }

    private List<User> makeUserList(Dealer dealer, Players players) {
        List<User> users = new ArrayList<>();
        users.add(dealer);
        for (Player player : players.getPlayers()) {
            users.add(player);
        }
        return users;
    }

    private void playGame(Dealer dealer, Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            addCardPerPlayer(player, deck);
        }
        while (dealer.isPossibleToAdd()) {
            OutputView.printAddDealerCard();
            dealer.requestCard(deck.getCard());
        }
        OutputView.printTotalResult(makeUserList(dealer, players));
    }

    private void addCardPerPlayer(Player player, Deck deck) {
        while (player.isPossibleToAdd() && InputView.askOneMoreCard(player)) {
            player.requestCard(deck.getCard());
            OutputView.printPlayerCard(player);
        }
    }

    private void handOutInitCard(Dealer dealer, Players players, Deck deck) {
        for (int i = 0; i < HAND_OUT_COUNT; i++) {
            dealer.dealCard(deck.getCard());
            players.dealCardToPlayers(deck);
        }
    }
}
