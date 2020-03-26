package domain.card;

public interface Deck {
    Deck shuffle();
    Card pop();
}
