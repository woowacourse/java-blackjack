package domain.card;

import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {
    private final Stack<Card> cards;

    public CardDeck() {
        Stack<Card> cards = createCards();
        Collections.shuffle(cards);
        this.cards = cards;
    }

    private static Stack<Card> createCards() {
        return Stream.of(Symbol.values())
                .map(CardDeck::createCardsBy)
                .flatMap(Stack::stream)
                .collect(Collectors.toCollection(Stack::new));
    }

    private static Stack<Card> createCardsBy(Symbol symbol) {
        return Stream.of(Type.values())
                .map(type -> new Card(type, symbol))
                .collect(Collectors.toCollection(Stack::new));
    }

    public Card pick() {
        if (cards.isEmpty()) {
            throw new RuntimeException("더 이상 뽑을 카드가 없습니다.");
        }
        return cards.pop();
    }
}
