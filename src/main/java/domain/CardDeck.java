package domain;

import domain.card.Card;
import exception.ErrorException;
import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    private static final int DECK_SIZE = 52;

    private final List<Card> cards;

    public CardDeck(List<Card> cards) {
        validateSize(cards);
        this.cards = new ArrayList<>(cards);
    }

    public Card pickCard() {
        return cards.removeFirst();
    }

    public List<List<Card>> pickInitialCardsStack(int stackSize) {
        List<List<Card>> cardsStack = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Card> cards = pickInitialCards(stackSize);
            cardsStack.add(cards);
        }
        return cardsStack;
    }

    private List<Card> pickInitialCards(int stackSize) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < stackSize; i++) {
            cards.add(pickCard());
        }
        return cards;
    }

    private void validateSize(List<Card> cards) {
        if (cards.size() != DECK_SIZE) {
            throw new ErrorException("카드는 총 " + DECK_SIZE + "장이어야 합니다.");
        }
    }
}
