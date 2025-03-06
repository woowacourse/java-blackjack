package domain;

import java.util.List;

public record WinLossCountResult(
        long winCount,
        long lossCount,
        long drawCount
) {

    public static WinLossCountResult computeWinLoss(List<Player> players, Dealer dealer) {
        long winCount = players.stream()
                .filter(player -> player.getHandTotal() < dealer.getHandTotal())
                .count();

        long drawCount = players.stream()
                .filter(player -> player.getHandTotal() == dealer.getHandTotal())
                .count();

        long lossCount = players.stream()
                .filter(player -> player.getHandTotal() > dealer.getHandTotal())
                .count();

        return new WinLossCountResult(winCount, lossCount, drawCount);
    }
}
