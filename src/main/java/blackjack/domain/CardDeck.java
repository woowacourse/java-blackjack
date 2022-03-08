package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private static final int INIT_PROVIDING_CARD_SIZE = 2;

    private final Queue<Card> cards;

    public CardDeck(final Queue<Card> cards) {
        this.cards = cards;
    }

    public List<Card> provideInitCards() {
        if (cards.size() < INIT_PROVIDING_CARD_SIZE) {
            throw new IllegalStateException("[Error] 남은 카드가 2장 미만입니다.");
        }
        return Arrays.asList(cards.poll(), cards.poll());
    }

    public Card provideCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("[Error] 남은 카드가 없습니다.");
        }
        return null;
    }
}
