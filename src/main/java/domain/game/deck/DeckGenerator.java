package domain.game.deck;

@FunctionalInterface
public interface DeckGenerator {
    Deck generate();
}
