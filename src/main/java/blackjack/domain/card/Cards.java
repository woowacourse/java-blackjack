package blackjack.domain.card;

import java.util.Collections;
import java.util.List;

public class Cards {

    private static final String FIRST_RECEIVED_CARD_SIZE_EXCEPTION_MESSAGE = "처음 제공받는 카드는 2장이어야 합니다.";
    private static final int FIRST_RECEIVED_CARD_SIZE = 2;

    private List<Card> cards;

    public Cards(List<Card> cards) {
        checkFirstReceivedCardsSize(cards.size());
        this.cards = cards;
    }

    private void checkFirstReceivedCardsSize(int size) {
        if (size != FIRST_RECEIVED_CARD_SIZE) {
            throw new IllegalArgumentException(FIRST_RECEIVED_CARD_SIZE_EXCEPTION_MESSAGE);
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }
}
