package blackjack.domain;

public interface Gamer {

    int BLACKJACK = 21;

    boolean canDraw();

    int calculateScore();

    boolean isBust();

    boolean isBlackjack();
}
