package blackjack.domain.deck;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private static final String NOT_EXIST_CARD_IN_DECK = "[ERROR] 덱에 더이상 카드가 없습니다.";
    private static final int FIRST_DRAW_CARD_COUNT = 2;

    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    public Card draw() {
        validateCardsSize();
        return cards.remove(cards.size() - 1);
    }

    private void validateCardsSize() {
        if (cards.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException(NOT_EXIST_CARD_IN_DECK);
        }
    }

    public List<Card> drawFirstCards() {
        return Stream.iterate(0, count -> count + 1)
                .map(count -> draw())
                .limit(FIRST_DRAW_CARD_COUNT)
                .collect(Collectors.toList());
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
