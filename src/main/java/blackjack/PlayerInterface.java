package blackjack;

public interface PlayerInterface {
    boolean isBust();

    void hit(TrumpCard card);

    String getName();
}
