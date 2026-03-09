package domain.card;

import domain.RandomValueGenerator;
import java.util.HashSet;
import java.util.List;
import util.ErrorMessage;

public class Deck {
    public static final int CARD_SIZE = 52;

    private final List<Card> cards;
    private final RandomValueGenerator randomValueGenerator;

    public Deck(List<Card> cards, RandomValueGenerator randomValueGenerator) {
        validateSize(cards);
        validateDuplicate(cards);
        this.cards = cards;
        this.randomValueGenerator = randomValueGenerator;
    }

    private void validateSize(List<Card> cards) {
        if (cards.size() != CARD_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.DECK_SIZE.getMessage());
        }
    }

    private void validateDuplicate(List<Card> cards) {
        if (new HashSet<>(cards).size() != cards.size()) {
            throw new IllegalArgumentException(ErrorMessage.DECK_DUPLICATE.getMessage());
        }
    }

    public Card drawCard() {
        int idx = randomValueGenerator.generate(getSize());
        if (idx < 0 || idx > cards.size()) {
            throw new IllegalArgumentException(ErrorMessage.INDEX_RANGE.getMessage());
        }
        Card card = cards.get(idx);
        cards.remove(idx);

        return card;
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    private int getSize() {
        return cards.size();
    }
}
