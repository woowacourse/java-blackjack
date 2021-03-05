package blackjack.domain.card;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {
    private final Deque<Card> deck;

    private CardDeck(Deque<Card> deck) {
        this.deck = deck;
    }

    public static CardDeck createDeck() {
        List<Card> cards = Arrays.stream(CardNumber.values())
                .flatMap(CardDeck::mapToSuit)
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        Deque<Card> deck = new ArrayDeque<>(cards);

        return new CardDeck(deck);
    }

    private static Stream<Card> mapToSuit(CardNumber cardNumber) {
        return Arrays.stream(Suit.values())
                .map(suit -> new Card(suit, cardNumber));
    }

    public Deque<Card> getDeck() {
        return deck;
    }

    public Card drawCard() {
        if(deck.isEmpty()){
            throw new NoSuchElementException("모든 카드를 소진하였습니다. 더이상 게임을 진행할 수 없습니다.");
        }

        return deck.pop();
    }
}
