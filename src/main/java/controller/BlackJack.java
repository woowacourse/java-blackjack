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
        Map<Player, Integer> profitPerParticipant = accountant.calculateProfit(players, dealer);
        printProfitPerParticipant(profitPerParticipant, dealer);
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

}
