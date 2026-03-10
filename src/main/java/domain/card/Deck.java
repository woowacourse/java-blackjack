package domain.card;

import domain.card.vo.Card;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;

public class Deck {
    public static final int CARD_SIZE_UNIT = 52;

    private final Deque<Card> cards;

    private Deck(Deque<Card> cards) {
        validateSize(cards);
        validateDuplicate(cards);
        this.cards = cards;
    }

    public static Deck createFromDeckMaker(DeckMaker deckMaker) {
        Deque<Card> deque = new ArrayDeque<Card>(deckMaker.make());
        return new Deck(deque);
    }

    public static Deck createFromList(List<Card> cards) {
        return new Deck(new ArrayDeque<>(cards));
    }

    private void validateSize(Deque<Card> cards) {
        if (cards.size() % CARD_SIZE_UNIT != 0) {
            throw new IllegalArgumentException("카드 사이즈는 %d단위 이어야 합니다.".formatted(Deck.CARD_SIZE_UNIT));
        }
    }

    private void validateDuplicate(Deque<Card> cards) {
        if (new HashSet<>(cards).size() != cards.size()) {
            throw new IllegalArgumentException("카드는 중복될 수 없습니다.");
        }
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("Deck is empty.");
        }
        return cards.pollFirst();
    }
}
