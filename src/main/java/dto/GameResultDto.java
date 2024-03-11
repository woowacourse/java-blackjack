package dto;

import domain.gamer.Dealer;
import domain.gamer.Players;
import java.util.List;

public record GameResultDto(List<Boolean> gameResult, int dealerWinCount) {
    public static GameResultDto makeGameResultDto(Players players, Dealer dealer) {
        List<Boolean> gameResult = players.getResult(dealer.getMaxGameScore());
        int dealerWinCount = (int) gameResult.stream()
                .filter(winOrLose -> !winOrLose)
                .count();

        return new GameResultDto(gameResult, dealerWinCount);
    }
}
