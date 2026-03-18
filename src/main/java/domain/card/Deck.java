package domain.card;

import static message.ErrorMessage.DECK_CAN_NOT_DUPLICATED;
import static message.ErrorMessage.DECK_OUT_OF_CARD_STOCK;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import service.CardGenerator;

public class Deck {
    private final Queue<Card> cards = new ArrayDeque<>();

    private Deck(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public static Deck create(CardGenerator cardGenerator) {
        List<Card> generatedCards = cardGenerator.generate();
        validateDuplicatedCards(generatedCards);
        return new Deck(generatedCards);
    }

    private static void validateDuplicatedCards(List<Card> cards) {
        Set<Card> distinctCards = Set.copyOf(cards);

        if (distinctCards.size() != cards.size()) {
            throw new IllegalArgumentException(DECK_CAN_NOT_DUPLICATED.getMessage());
        }
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public Card drawCard() {
        if (cards.peek() == null) {
            throw new IllegalStateException(DECK_OUT_OF_CARD_STOCK.getMessage());
        }

        return cards.poll();
    }
}
