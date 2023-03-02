package blackjack.domain;

public interface Player {

    void add(Card card);
    int getPoint();
    boolean canReceive();
}
