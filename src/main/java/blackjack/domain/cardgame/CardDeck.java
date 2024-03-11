package blackjack.domain.cardgame;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class CardDeck {
    private final Stack<Card> deck;

    private CardDeck(Stack<Card> deck) {
        this.deck = deck;
    }

    static CardDeck createShuffledDeck() {
        final List<Card> allKindOfCards = Arrays.stream(CardShape.values())
                .flatMap(CardDeck::createEachNumber)
                .toList();

        final Stack<Card> deck = new Stack<>();
        for (final Card card : allKindOfCards) {
            deck.push(card);
        }

        Collections.shuffle(deck);
        return new CardDeck(deck);
    }

    private static Stream<Card> createEachNumber(final CardShape cardShape) {
        return Arrays.stream(CardNumber.values())
                .map(cardNumber -> new Card(cardNumber, cardShape));
    }

    Card draw() {
        if (deck.empty()) {
            throw new IllegalArgumentException("카드가 존재하지 않습니다.");
        }

        return deck.pop();
    }
}
