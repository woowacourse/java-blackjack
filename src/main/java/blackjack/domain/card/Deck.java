package blackjack.domain.card;

import blackjack.domain.card.pattern.Denomination;
import blackjack.domain.card.pattern.Suit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Deck {

    private static final int EMPTY_DECK_SIZE = 0;

    private final Stack<Card> cards;

    private Deck(final Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck initializeDeck() {
        Stack<Card> cards = new Stack<>();
        for (Suit suit : Suit.values()) {
            cards.addAll(createCardsByDenominationValue(suit));
        }
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    private static List<Card> createCardsByDenominationValue(final Suit suit) {
        return Arrays.stream(Denomination.values())
            .map(denomination -> new Card(suit, denomination))
            .collect(Collectors.toList());
    }

    public Card draw() {
        checkCardSize();
        return cards.pop();
    }

    private void checkCardSize() {
        if (cards.size() == EMPTY_DECK_SIZE) {
            throw new IllegalStateException("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
        }
    }

}
