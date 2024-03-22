package blackjack.domain.card.deck;

import blackjack.domain.card.Card;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {
    static final int INIT_SIZE = 52;

    private final Deque<Card> cards;

    public Deck(final DeckCardGenerator deckCardGenerator) {
        this(deckCardGenerator.generate());
    }

    public Deck(final Deque<Card> cards) {
        Deque<Card> copyDeck = new ArrayDeque<>(cards);
        validate(copyDeck);
        this.cards = copyDeck;
    }

    private void validate(final Deque<Card> cards) {
        validateDuplicate(cards);
        validateSize(cards);
    }

    private void validateDuplicate(final Deque<Card> cards) {
        int distinctSize = new HashSet<>(cards).size();

        if (cards.size() != distinctSize) {
            throw new IllegalArgumentException("카드는 중복될 수 없습니다.");
        }
    }

    private void validateSize(final Deque<Card> deck) {
        if (deck.size() != INIT_SIZE) {
            throw new IllegalArgumentException(String.format("덱의 사이즈가 %d장이 아닙니다.", INIT_SIZE));
        }
    }

    public List<Card> drawInitCards() {
        return List.of(drawCard(), drawCard());
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.pop();
        }
        throw new NoSuchElementException("덱에 카드가 존재하지 않습니다.");
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
