package domain.card;

public interface Deck {
    Card pop();
    Deck shuffle();
}
