package blackjack.domain.vo;

public record GameResult(
    String name,
    int profit
) {
    public static GameResult from(String name, int profit) {
        return new GameResult(name, profit);
    }
}
