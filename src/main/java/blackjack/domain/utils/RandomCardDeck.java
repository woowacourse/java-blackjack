package blackjack.domain.utils;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denominations;
import blackjack.domain.card.Suits;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class RandomCardDeck implements CardDeck {
    private static final String EXCEPTION_RUN_OUT_OF_CARDS = "카드가 소진되었습니다.";

    private final Queue<Card> cards;

    public RandomCardDeck() {
        LinkedList<Card> cardsValue = new LinkedList<>();
        for (Suits suit : Suits.values()) {
            assembleWithDenominations(cardsValue, suit);
        }
        Collections.shuffle(cardsValue);
        this.cards = cardsValue;
    }

    @Override
    public void assembleWithDenominations(LinkedList<Card> cardsValue, Suits suit) {
        for (Denominations denomination : Denominations.values()) {
            cardsValue.add(Card.from(denomination, suit));
        }
    }

    @Override
    public Card pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException(EXCEPTION_RUN_OUT_OF_CARDS);
        }
        return cards.poll();
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }


}
