package blackjack.domain.blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private final Deque<Card> value;

    public Deck(final Deque<Card> cards) {
        this.value = cards;
    }

    public static Deck createPack() {
        final List<Card> cards = createCards();
        Collections.shuffle(cards);
        return new Deck(new ArrayDeque<>(cards));
    }

    private static List<Card> createCards() {
        return Arrays.stream(CardSymbol.values())
                     .flatMap(Deck::addCard)
                     .collect(Collectors.toCollection(ArrayList::new));
    }

    private static Stream<Card> addCard(final CardSymbol cardSymbol) {
        return Arrays.stream(CardValue.values())
                     .map(cardNumber -> new Card(cardNumber, cardSymbol));
    }

    public Card draw() {
        final var card = value.pollLast();

        if (card == null) {
            throw new IllegalStateException("카드가 전부 소진되었습니다.");
        }
        
        return card;
    }
}
