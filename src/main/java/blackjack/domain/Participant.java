package blackjack.domain;

public interface Participant {
    void draw(Deck deck);

    int score();

    void drawMoreCard(Deck deck);

    boolean isDealer();

    boolean isBusted();

    String handStatus();

    String getName();

    String gameResult();
}
