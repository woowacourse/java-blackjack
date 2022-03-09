package blackjack.domain;

public interface Player {

    void addCard(Card card);

    boolean isOverLimit(int limit);

    String getName();

    Deck getDeck();
}
