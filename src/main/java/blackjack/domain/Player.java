package blackjack.domain;

public interface Player {

    void addCard(Card card);

    boolean isOverLimit(int limit);

    boolean isDealer();

    String getName();

    Deck getDeck();
}
