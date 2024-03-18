package model.card;

import java.util.*;
import java.util.stream.Stream;

public class CardDeck {
    private final Set<Card> cards;

    public CardDeck(List<Card> cards) {
        this(new HashSet<>(cards));
    }

    public CardDeck(Set<Card> cards) {
        this.cards = cards;
    }

    public Cards selectRandomCards(CardSize size) {
        List<Card> cards = Stream.generate(this::selectRandomCard)
                .limit(size.getSize())
                .toList();

        return new Cards(cards);
    }

    public Card selectRandomCard() {
        validateCardDeckNotEmpty();
        int removeIndex = new Random().nextInt(cards.size());
        Card card = new ArrayList<>(cards).remove(removeIndex);
        cards.remove(card);
        return card;
    }

    private void validateCardDeckNotEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("더 뽑을 카드가 없습니다.");
        }
    }
}
