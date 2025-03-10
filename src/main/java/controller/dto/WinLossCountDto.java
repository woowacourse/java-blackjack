package controller.dto;

import domain.Dealer;
import domain.Player;
import java.util.List;

public record WinLossCountDto(
        long winCount,
        long lossCount,
        long drawCount
) {

    public static WinLossCountDto computeWinLoss(List<Player> players, Dealer dealer) {
        long winCount = players.stream()
                .filter(player -> player.getHandTotal() < dealer.getHandTotal())
                .count();

        long drawCount = players.stream()
                .filter(player -> player.getHandTotal() == dealer.getHandTotal())
                .count();

        long lossCount = players.stream()
                .filter(player -> player.getHandTotal() > dealer.getHandTotal())
                .count();
        return new WinLossCountDto(winCount, lossCount, drawCount);
    }
}
