package blackjack.domain.result;

import java.util.*;

import blackjack.domain.player.BetMoney;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class Profits {

    private final PlayersBetMoney playersBetMoney;

    public Profits(PlayersBetMoney playersBetMoney) {
        this.playersBetMoney = playersBetMoney;
    }

    public void competeDealerWithPlayers(Players players) {
        Map<Player, BetMoney> playersMoney = playersBetMoney.getPlayersMoney();
        for (Player player : playersMoney.keySet()) {
            scoreResultIfGuest(players, player);
        }
        scoreDealer(players);
    }

    private void scoreResultIfGuest(Players players, Player guest) {
        if (guest.isDealer()) {
            return;
        }
        scoreEachGuest(players, guest);
    }

    private void scoreEachGuest(Players players, Player guest) {
        Player dealer = players.getDealer();
        Match match = Match.findWinner(guest, dealer);
        scoreWinOrBlackjackWin(players, guest, match);
        scoreLoseOrBlackjackLose(players, guest, match);
    }

    private void scoreWinOrBlackjackWin(Players players, Player guest, Match match) {
        if (match.isMatchBlackjackWin()) {
            scoreBlackjackWin(players, guest);
        }
        if (match.isMatchWin()) {
            scoreWin(players, guest);
        }
    }

    private void scoreBlackjackWin(Players players, Player guest) {
        playersBetMoney.calcPlusBlackjackMoney(players, guest);
    }

    private void scoreWin(Players players, Player guest) {
        playersBetMoney.calcPlusMoney(players, guest);
    }

    private void scoreLoseOrBlackjackLose(Players players, Player guest, Match match) {
        if (match.isMatchLose()) {
            scoreLose(players, guest);
        }
        if (match.isMatchBlackjackLose()) {
            scoreBlackjackLose(players, guest);
        }
    }

    private void scoreLose(Players players, Player guest) {
        playersBetMoney.calcMinusMoney(players, guest);
    }

    private void scoreBlackjackLose(Players players, Player guest) {
        playersBetMoney.calcMinusBlackjackMoney(players, guest);
    }

    private void scoreDealer(Players players) {
        playersBetMoney.calcDealerMoney(players);
    }

    public PlayersBetMoney getPlayersBetMoney() {
        return playersBetMoney;
    }
}
