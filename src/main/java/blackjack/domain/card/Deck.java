package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

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
            addSuitAndDenomination(suit, cardsPool);
        }
        Collections.shuffle(cardsPool);

        return cardsPool;
    }

    private void addSuitAndDenomination(Suit suit, List<Card> cardsPool) {
        for (Denomination denomination : Denomination.values()) {
            cardsPool.add(new Card(suit, denomination));
        }
    }

}
