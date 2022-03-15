package blackjack.domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

public class Deck {

    private static final String RUN_OUT_OF_CARDS_ERROR_MESSAGE = "[ERROR] 카드를 모두 사용하였습니다.";

    private final Deque<Card> cards;

    public Deck() {
        this.cards = new ArrayDeque<>(generateAndShuffleCards());
    }

    public Card pickCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException(RUN_OUT_OF_CARDS_ERROR_MESSAGE);
        }
        return cards.pop();
    }

    private List<Card> generateAndShuffleCards() {
        List<Card> cardsPool = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            Stream.of(Denomination.values())
                .forEach(denomination -> cardsPool.add(new Card(suit, denomination)));
        }
        Collections.shuffle(cardsPool);

        return cardsPool;
    }
}
