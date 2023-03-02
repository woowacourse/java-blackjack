package blackjack.domain;

public interface Player {
    String getName();

    void initialDraw(final Deck deck);

    boolean canDraw();
}
