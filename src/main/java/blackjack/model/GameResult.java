package blackjack.model;

public record GameResult(String name, int profit) {
    public static GameResult from(User user, int profit) {
        return new GameResult(
                user.getName(),
                profit
        );
    }
}
