package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;

public final class Deck {

    private static final int CARD_TOTAL_SIZE = 52;

    private static Stack<Card> cards;
    private static final Deck DECK = new Deck();

    private Deck() {
    }

    public static Deck initializeCards() {
        DECK.createCards();
        DECK.shuffleCards();
        return DECK;
    }

    private void createCards() {
        Stack<Card> newCards = Arrays.stream(CardSymbol.values())
                .flatMap(cardSymbol -> Arrays.stream(CardNumber.values())
                        .map(cardNumber -> new Card(cardNumber, cardSymbol)))
                .collect(Collectors.toCollection(Stack::new));
        validate(newCards);
        cards = newCards;
    }

    private void validate(List<Card> cards) {
        if (cards.size() != CARD_TOTAL_SIZE) {
            throw new IllegalArgumentException("카드의 개수는 총 48개여야 합니다.");
        }
    }

    private void shuffleCards() {
        Collections.shuffle(cards);
    }

    public Stack<Card> getCards() {
        return cards;
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new EmptyStackException();
        }
        return cards.pop();
    }
}
