package domain.user;

import domain.card.Card;
import domain.card.CardNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardPool {

    private static final int CARD_POINT_LIMIT = 21;

    private final List<Card> cards;

    public CardPool(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateTotalPoint() {
        int sum = getSumOfCards();
        sum = updateIfExceedLimitAndAceExists(sum);
        return sum;
    }

    private int getSumOfCards() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getNumber().getPoint();
        }
        return sum;
    }

    private int updateIfExceedLimitAndAceExists(final int sum) {
        int updatedSum = sum;
        if (isTotalPointExceedLimit() && containsAce()) {
            updatedSum -= CARD_POINT_LIMIT - CardNumber.ACE.getPoint();
        }
        return updatedSum;
    }

    public boolean isTotalPointExceedLimit() {
        return getSumOfCards() > CARD_POINT_LIMIT;
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(card -> card.hasNumberOf(CardNumber.ACE));
    }

    public boolean isSumSameAsLimit() {
        return calculateTotalPoint() == CARD_POINT_LIMIT;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
