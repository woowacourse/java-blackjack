package controller.dto;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;

public record WinLossCountDto(
        long winCount,
        long lossCount,
        long drawCount
) {

    public static WinLossCountDto computeWinLoss(Players players, Dealer dealer) {
        List<Player> allPlayers = players.getPlayers();
        long winCount = allPlayers.stream()
                .filter(player -> player.getHandTotal() < dealer.getHandTotal())
                .count();

        long drawCount = allPlayers.stream()
                .filter(player -> player.getHandTotal() == dealer.getHandTotal())
                .count();

        long lossCount = allPlayers.stream()
                .filter(player -> player.getHandTotal() > dealer.getHandTotal())
                .count();
        return new WinLossCountDto(winCount, lossCount, drawCount);
    }
}
