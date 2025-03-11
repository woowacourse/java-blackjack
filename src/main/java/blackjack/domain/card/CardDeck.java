package blackjack.domain.card;

import blackjack.exception.ExceptionMessage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private final CardGenerator cardGenerator;
    private final Queue<Card> cards = new ArrayDeque<>();

    public CardDeck(CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
    }

    public void initialize() {
        List<Card> shuffledCards = cardGenerator.makeShuffled();
        this.cards.clear();
        this.cards.addAll(shuffledCards);
    }

    public List<Card> drawCard(int count) {
        validateEmptyCardDeck(count);
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            drawnCards.add(cards.poll());
        }
        return drawnCards;
    }

    public int getSize() {
        return cards.size();
    }

    private void validateEmptyCardDeck(int count) {
        if (cards.size() < count) {
            throw new IllegalArgumentException(ExceptionMessage.EMPTY_CARD_DECK.getContent());
        }
    }
}
