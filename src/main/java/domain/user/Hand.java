package domain.user;

import domain.card.Card;
import domain.card.Denomination;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;
    private static final int MIN_ACE = 1;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int sumCardNumbers() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getDenomination().value();
        }

        sum = decideAceNumber(sum, countAce());

        return sum;
    }

    private int decideAceNumber(int sum, int unDecidedAceCount) {
        if (sum > BLACKJACK_NUMBER && unDecidedAceCount > 0) {
            sum = sum - (Denomination.ACE.value() - MIN_ACE);
            return decideAceNumber(sum, unDecidedAceCount - 1);
        }
        return sum;
    }

    public HandStatus getStatus() {
        if (sumCardNumbers() == BLACKJACK_NUMBER && cards.size() == BLACKJACK_CARD_SIZE) {
            return HandStatus.BLACKJACK;
        }

        if (sumCardNumbers() > BLACKJACK_NUMBER) {
            return HandStatus.BUST;
        }

        return HandStatus.CAN_HIT;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(card -> card.getDenomination() == Denomination.ACE)
                .count();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
