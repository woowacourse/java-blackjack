package domain.rule;

public interface Drawable {
    public static final int ACE_BONUS_SCORE = 10;
    public static final int BLACKJACK_FULL_SCORE = 21;

    abstract boolean check(int score);
}
