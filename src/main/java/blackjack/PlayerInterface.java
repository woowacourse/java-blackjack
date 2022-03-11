package blackjack;

public interface PlayerInterface {
    void addCard(TrumpCard card);

    boolean isBust();

    void hit(TrumpCard card);

    String getName();
}
