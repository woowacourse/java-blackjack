package domain;

import domain.gamer.Player;
import domain.judge.WinState;

import java.util.List;

public class Cashier {

    private final List<PlayerBet> playerBets;

    public Cashier(List<PlayerBet> playerBets) {
        this.playerBets = playerBets;
    }

    public int calculateProfit(Player player, WinState winState) {
        BetAmount betAmount = playerBets.stream()
                .filter(playerBet -> playerBet.getPlayer().equals(player))
                .map(PlayerBet::getBetAmount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 플레이어가 없습니다."));

        return calculateProfitByWinState(winState, betAmount);
    }

    private int calculateProfitByWinState(WinState winState, BetAmount betAmount) {
        if (winState.equals(WinState.WIN)) {
            return betAmount.winAmount();
        }
        if (winState.equals(WinState.LOSE)) {
            return betAmount.loseAmount();
        }
        if (winState.equals(WinState.DRAW)) {
            return betAmount.drawAmount();
        }
        if (winState.equals(WinState.BLACK_JACK)) {
            return betAmount.blackJackAmount();
        }
        return 0;
    }
}
