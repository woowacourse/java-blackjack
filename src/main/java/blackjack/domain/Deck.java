package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Deck {

    static final String EMPTY_CARD_EXCEPTION_MESSAGE = "[ERROR] 52장의 카드가 모두 소진되었습니다.";
    private final LinkedList<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new LinkedList<>(cards);
    }

    public static Deck createFixedCards() {
        List<Card> cards = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Denomination.values())
                        .map(denomination -> new Card(denomination, suit)))
                        .collect(Collectors.toList());
        return new Deck(cards);
    }

    public Card draw() {
        try {
            return cards.pop();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(EMPTY_CARD_EXCEPTION_MESSAGE);
        }
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
