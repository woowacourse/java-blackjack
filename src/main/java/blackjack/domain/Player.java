package blackjack.domain;

public interface Player {

    void addCard(Card card);

    boolean isOverLimit();

    boolean isDealer();

    String getName();

    Deck getDeck();
}
