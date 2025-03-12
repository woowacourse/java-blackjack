package controller;

import static view.InputView.accountBettingPrice;
import static view.InputView.getPlayerNamesInput;
import static view.OutputView.printDealerExtraCardsCount;
import static view.OutputView.printDistributeResult;
import static view.OutputView.printEveryOneCardsNamesWithTotal;
import static view.OutputView.printProfitPerParticipant;

import domain.Accountant;
import domain.Dealer;
import domain.Deck;
import domain.Player;
import domain.Players;
import domain.WinLossResult;
import java.util.HashMap;
import java.util.Map;

public class BlackJack {

    private Players players;
    private final Dealer dealer = new Dealer();

    private final Deck deck = new Deck();
    private final Accountant accountant = new Accountant();

    public void play() {
        preset();
        playersTurn();
        dealersTurn();
        printResult();
    }

    private void preset() {
        players = new Players(getPlayerNamesInput());
        accountBettingPrice(players, accountant);
        deck.distributeCards(dealer, players);
        printDistributeResult(players, dealer);
    }

    private void playersTurn() {
        for (Player player : players.getPlayers()) {
            player.drawByChoice(deck);
        }
    }

    public void dealersTurn() {
        dealer.drawWithThreshold(deck);
    }

    private void printResult() {
        printDealerExtraCardsCount(dealer);
        printEveryOneCardsNamesWithTotal(players, dealer);
        Map<Player, Integer> profitPerParticipant = calculateProfit();
        printProfitPerParticipant(profitPerParticipant, dealer);
//        printWinLossResult(players, dealer, countWinLoss(players, dealer));
    }

    private Map<Player, Integer> calculateProfit() {
        Map<Player, Integer> profitPerParticipant = new HashMap<>();
        int dealersProfit = 0;
        for (Player player : players.getPlayers()) {
            int playersProfit = accountant.getProfit(player, computeWinLoss(player, dealer));
            profitPerParticipant.put(player, playersProfit);
            dealersProfit -= playersProfit;
        }
        profitPerParticipant.put(dealer, dealersProfit);
        return profitPerParticipant;
    }


    private WinLossResult computeWinLoss(Player player, Dealer dealer) {
        int playerScore = player.getHandTotal();
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return WinLossResult.WIN_WITH_BLACK_JACK;
        }
        if (playerScore > dealer.getHandTotal()) {
            return WinLossResult.WIN;
        }
        if (playerScore == dealer.getHandTotal()) {
            return WinLossResult.DRAW;
        }
        return WinLossResult.LOSS;
    }

//    public WinLossCountDto countWinLoss(Players players, Dealer dealer) {
//        ArrayList<Integer> winLossDraw = new ArrayList<>(Arrays.asList(0,0,0));
//        int dealerScore = dealer.getHandTotal();
//        players.getPlayers().forEach(player -> {
//            // winLossResult가 나옴
//            int playerScore = player.getHandTotal();
//            if (playerScore < dealerScore) {
//                winLossDraw.add(0, 1);
//            }
//            if (playerScore > dealerScore) {
//                winLossDraw.add(1, 1);
//            }
//            if (playerScore == dealerScore) {
//                winLossDraw.add(2, 1);
//            }
//        });
//        return new WinLossCountDto(winLossDraw.get(0), winLossDraw.get(1), winLossDraw.get(2));
//    }

}
