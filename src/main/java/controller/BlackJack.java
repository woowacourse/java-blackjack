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
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJack {

    public void play() {
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        Accountant accountant = new Accountant();
        Players players = preset(dealer, deck, accountant);
        playersTurn(players, deck);
        dealersTurn(dealer, deck);
        printResult(players, dealer, accountant);
    }

    private Players preset(Dealer dealer, Deck deck, Accountant accountant) {
        Players players = new Players(getPlayerNamesInput());
        accountBettingPrice(players, accountant);
        deck.distributeCards(dealer, players);
        printDistributeResult(players, dealer);
        return players;
    }

    private void playersTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            player.drawByChoice(deck);
        }
    }

    public void dealersTurn(Dealer dealer, Deck deck) {
        dealer.drawWithThreshold(deck);
    }

    private void printResult(Players players, Dealer dealer, Accountant accountant) {
        printDealerExtraCardsCount(dealer);
        printEveryOneCardsNamesWithTotal(players, dealer);
        Map<Player, Integer> profitPerParticipant = calculateProfit(players, dealer, accountant);
        printProfitPerParticipant(profitPerParticipant, dealer);
//        printWinLossResult(players, dealer, countWinLoss(players, dealer));
    }

    private Map<Player, Integer> calculateProfit(Players players, Dealer dealer, Accountant accountant) {
        Map<Player, Integer> profitPerParticipant = new LinkedHashMap<>();
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
