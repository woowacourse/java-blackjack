package blackJack.controller;

import blackJack.domain.Card.Cards;
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

import static blackJack.domain.Card.CardFactory.CARD_CACHE;

public class Blackjack {

    private static final int HAND_OUT_COUNT = 2;

    public void run() {
        final Dealer dealer = new Dealer();
        final Cards cards = new Cards(CARD_CACHE);
        List<String> playerNames = InputView.inputPlayerNames();
        Map<String, Integer> bettingMoneys = getBettingMoneys(playerNames);
        final Players players = new Players(playerNames, bettingMoneys);

        handOutInitCard(dealer, players);

        OutputView.printDrawMessage(playerNames);
        OutputView.printTotalUserCards(dealer, players);

        if (checkDealerIsBlackJack(dealer)) {
            Map<String, Integer> playersProfits = makeBlackjackResult(players);
            int dealerProfit = Result.makeDealerResult(playersProfits);
            printFinalResult(dealer, playersProfits, dealerProfit);
            return;
        }

        playGame(dealer, players);
        OutputView.printTotalResult(makeUserList(dealer, players));

        Map<String, Integer> playerProfit = Result.makePlayerResult(dealer, players);
        int dealerProfit = Result.makeDealerResult(playerProfit);

        printFinalResult(dealer, playerProfit, dealerProfit);
    }

    public Map<String, Integer> makeBlackjackResult(Players players) {
        Map<Player, Result> results = new HashMap<>();
        for (Player player : players.getPlayers()) {
            results.put(player, player.judgeByBlackjack());
        }
        return Result.calculateProfit(results);
    }

    private void printFinalResult(Dealer dealer, Map<String, Integer> playerResults, int dealerScore) {
        OutputView.printFinalResult(
                dealer.getName(),
                playerResults,
                dealerScore
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

    private void playGame(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            addCardPerPlayer(player);
        }
        while (dealer.isPossibleToAdd()) {
            OutputView.printAddDealerCard();
            dealer.requestCard(CARD_CACHE.poll());
        }
    }

    private void addCardPerPlayer(Player player) {
        while (player.isPossibleToAdd() && InputView.askOneMoreCard(player)) {
            player.requestCard(CARD_CACHE.poll());
            OutputView.printPlayerCard(player);
        }
    }

    private void handOutInitCard(Dealer dealer, Players players) {
        for (int i = 0; i < HAND_OUT_COUNT; i++) {
            dealer.dealCard(CARD_CACHE.poll());
            players.dealCardToPlayers();
        }
    }
    public boolean checkDealerIsBlackJack(Dealer dealer) {
        if (dealer.isBlackJack()) {
            return true;
        }
        return false;
    }
}
