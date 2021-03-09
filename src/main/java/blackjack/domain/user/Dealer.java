package blackjack.domain.user;

public class Dealer extends AbstractUser {
    public static final int TURN_OVER_COUNT = 16;

    private final String name = "딜러";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isGameOver(final int gameOverScore) {
        int score = getScore();
        return score > TURN_OVER_COUNT || score > gameOverScore;
    }
}
