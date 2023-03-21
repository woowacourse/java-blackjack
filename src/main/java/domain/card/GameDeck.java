package domain.card;

import exception.DeckHasNotRemainingCardException;

import java.util.ArrayList;
import java.util.List;

public final class GameDeck {
    private final List<Card> cards;

    public GameDeck(DeckGenerator deckGenerator) {
        cards = deckGenerator.generate();
    }

    public Card drawCard() {
        if (isEmpty()) {
            throw new DeckHasNotRemainingCardException();
        }
        return cards.remove(0);
    }

    public List<Card> drawForFirstTurn() {
        List<Card> firstTurnCards = new ArrayList<>();
        firstTurnCards.add(drawCard());
        firstTurnCards.add(drawCard());

        return firstTurnCards;
    }

    private boolean isEmpty() {
        return cards.isEmpty();
    }
}
