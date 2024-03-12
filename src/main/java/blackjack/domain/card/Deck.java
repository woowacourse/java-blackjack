package blackjack.domain.card;

@FunctionalInterface
public interface Deck {
    Card drawCard();
}
