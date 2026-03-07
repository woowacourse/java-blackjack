package domain.card;

import static message.ErrorMessage.DECK_CAN_NOT_DUPLICATED;

import java.util.ArrayDeque;
import java.util.List;
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

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card drawCard() {
        return cards.poll();
    }
}
