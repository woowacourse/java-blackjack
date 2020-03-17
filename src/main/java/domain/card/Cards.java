package domain.card;

import domain.CardCalculator;

import java.util.Collections;
import java.util.List;

public class Cards {
    private static final int BLACK_JACK_SIZE = 2;
    private static final int DEALER_STANDARD_ADDITIONAL_CARD = 16;
    private static final String NO_CARDS = "입력한 카드가 없습니다.";

    private List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards of(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new IllegalArgumentException(NO_CARDS);
        }

        return new Cards(cards);
    }

    public boolean isCardsSumUnderSixteen() {
        return getCardsSum() <= DEALER_STANDARD_ADDITIONAL_CARD;
    }

    public boolean containAce() {
        return this.cards.stream()
                .anyMatch(Card::isAce);
    }

    public void addCard(Card card) {
        if (card == null) {
            throw new NullPointerException(NO_CARDS);
        }

        this.cards.add(card);
    }

    public boolean isBlackJack() {
        return containAce()
                && this.cards.size() == BLACK_JACK_SIZE
                && CardCalculator.isCardSumBlackJack(this);
    }

    public int getCardsSum() {
        return CardCalculator.calculateAceStrategy(this);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
