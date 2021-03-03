package blackjack.domain;

public interface PayerInterface {
    void drawCard(final Card card);
    boolean isCanDraw();
    int getValue();
}
