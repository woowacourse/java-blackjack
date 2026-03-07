package blackjack.model;

public interface DealerDrawPolicy {
    boolean shouldDraw(int score);
}
