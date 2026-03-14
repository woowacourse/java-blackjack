package blackjack.domain.vo;

import blackjack.domain.Players;

public record GameResult(
    String name,
    int profit
) {
    public static GameResult from(String name, int profit) {
        return new GameResult(name, profit);
    }
}
