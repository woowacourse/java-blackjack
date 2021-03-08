package blackjack.domain;

import blackjack.domain.card.Card;

import java.util.*;

public class Deck {

    private final Queue<Card> cards;

    public Deck(List<Card> cards) {
        validateDuplication(cards);
        this.cards = new LinkedList<>(cards);
    }

    private void validateDuplication(List<Card> cards) {
        if (new HashSet<>(cards).size() != cards.size()) {
            throw new IllegalArgumentException("덱 내 중복되는 카드가 존재합니다.");
        }
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public Card draw() {
        Card card = cards.poll();
        if (card == null) {
            throw new IllegalStateException("덱이 모두 소진되었습니다.");
        }
        return card;
    }
}
