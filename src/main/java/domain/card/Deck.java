package domain.card;

import static message.ErrorMessage.DECK_CAN_NOT_DUPLICATED;
import static message.ErrorMessage.DECK_IS_EMPTY;

import java.util.ArrayDeque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class Deck {
    private final Queue<Card> cards = new ArrayDeque<>();

    public Deck(List<Card> cards) {
        validateDuplicatedCards(cards);
        this.cards.addAll(cards);
    }

    private void validateDuplicatedCards(List<Card> cards) {
        List<Card> distinctCards = cards.stream()
                .distinct()
                .toList();

        if (distinctCards.size() != cards.size()) {
            throw new IllegalArgumentException(DECK_CAN_NOT_DUPLICATED.getMessage());
        }
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException(DECK_IS_EMPTY.getMessage());
        }
        return cards.poll();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
