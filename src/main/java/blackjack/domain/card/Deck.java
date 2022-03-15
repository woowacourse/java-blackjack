package blackjack.domain.card;

import blackjack.domain.card.strategy.CardsGenerateStrategy;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Deck {

    static final String EMPTY_CARD_EXCEPTION_MESSAGE = "[ERROR] 52장의 카드가 모두 소진되었습니다.";

    private final LinkedList<Card> cards;

    public Deck(CardsGenerateStrategy cardsGenerateStrategy) {
        this.cards = cardsGenerateStrategy.generate();
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
