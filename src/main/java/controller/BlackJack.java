package controller;

import static view.InputView.getPlayerNamesInput;
import static view.OutputView.printDealerExtraCardsCount;
import static view.OutputView.printDistributeResult;
import static view.OutputView.printEveryOneCardsNamesWithTotal;
import static view.OutputView.printWinLossResult;

import controller.dto.WinLossCountDto;
import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Players;
import java.util.ArrayList;
import java.util.Arrays;

public class BlackJack {

    private Players players;
    private final Dealer dealer = new Dealer();
    private final Deck deck = new Deck();

    public void play() {
        preset();
        playersTurn();
        dealersTurn(dealer);
        printResult();
    }

    private void preset() {
        players = new Players(getPlayerNamesInput());
        deck.distributeCards(dealer, players);
        printDistributeResult(players, dealer);
    }

    private void playersTurn() {
        for (Player player : players.getPlayers()) {
            player.drawByChoice(deck);
        }
    }

    public void dealersTurn(Dealer dealer) {
        dealer.drawWithThreshold(deck);
    }

    private void printResult() {
        printDealerExtraCardsCount(dealer);
        printEveryOneCardsNamesWithTotal(players, dealer);
        printWinLossResult(players, dealer, computeWinLoss(players, dealer));
    }

    public WinLossCountDto computeWinLoss(Players players, Dealer dealer) {
        ArrayList<Integer> winLossDraw = new ArrayList<>(Arrays.asList(0,0,0));
        int dealerScore = dealer.getHandTotal();
        players.getPlayers().forEach(player -> {
            int playerScore = player.getHandTotal();
            if (playerScore < dealerScore) {
                winLossDraw.add(0, 1);
            }
            if (playerScore > dealerScore) {
                winLossDraw.add(1, 1);
            }
            if (playerScore == dealerScore) {
                winLossDraw.add(2, 1);
            }
        });
        return new WinLossCountDto(winLossDraw.get(0), winLossDraw.get(1), winLossDraw.get(2));
    }

}
