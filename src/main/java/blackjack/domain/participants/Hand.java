package blackjack.domain.participants;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    static final int SUM_MAXIMUM_BEFORE_BUST = 21;
    static final int ACE_ADDITIONAL_VALUE = 10;

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int sumScore() {
        validateStatus();
        final int sum = cards.stream()
                .mapToInt(Card::getDenominationValue)
                .sum();
        return addBonusScoreByAceCount(sum, countACE());
    }

    public boolean hasBustedScore() {
        return sumScore() > SUM_MAXIMUM_BEFORE_BUST;
    }

    public boolean hasScoreUnderMax() {
        return sumScore() < SUM_MAXIMUM_BEFORE_BUST;
    }

    private int countACE() {
        return (int) cards.stream()
                .filter(Card::isACE)
                .count();
    }

    private int addBonusScoreByAceCount(int sum, int aceCount) {
        while ((aceCount > 0) && ((sum + ACE_ADDITIONAL_VALUE) <= SUM_MAXIMUM_BEFORE_BUST)) {
            sum += ACE_ADDITIONAL_VALUE;
            aceCount--;
        }
        return sum;
    }

    public List<Card> cards() {
        validateStatus();
        return new ArrayList<>(cards);
    }


    private void validateStatus() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 존재하지 않는 핸드입니다.");
        }
    }
}
