package blackjack.domain;

public interface Player {
    void drawCard(final Card card);
    boolean isCanDraw();
    int getValue();
}
