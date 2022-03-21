package blackjack.domain.card;

import blackjack.domain.card.pattern.Denomination;
import blackjack.domain.card.pattern.Suit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Deck {

    private final Stack<Card> deck;

    private Deck(final Stack<Card> playerCards) {
        this.deck = playerCards;
    }

    public static Deck initializeDeck() {
        Stack<Card> deck = new Stack<>();
        for (Suit suit : Suit.values()) {
            deck.addAll(createCardsByDenominationValue(suit));
        }
        Collections.shuffle(deck);
        return new Deck(deck);
    }

    private static List<Card> createCardsByDenominationValue(final Suit suit) {
        return Arrays.stream(Denomination.values())
            .map(denomination -> new Card(suit, denomination))
            .collect(Collectors.toList());
    }

    public Card draw() {
        checkCardSize();
        return deck.pop();
    }

    private void checkCardSize() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
        }
    }

}
