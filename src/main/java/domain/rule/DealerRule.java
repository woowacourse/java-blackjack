package domain.rule;

public class DealerRule implements Drawable {
    public static final int DRAW_MAX_SCORE = 16;

    public boolean check(int score) {
        return score <= DRAW_MAX_SCORE;
    }
}
