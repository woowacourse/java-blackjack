package domain;

public class PlayerRule implements Drawable{
    public boolean check(int score) {
        return score <= BLACKJACK_FULL_SCORE;
    }
}
