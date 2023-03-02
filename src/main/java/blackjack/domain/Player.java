package blackjack.domain;

public interface Player {

    void draw(Card card);
    boolean canReceive();
    GamePoint getGamePoint();
}
