package blackjack.domain.calculator;

import blackjack.domain.card.Card;

import java.util.List;

class AceScoreStrategy extends DefaultScoreStrategy {

    private static final int ACE_WEIGHT = 10;
    private static final int MAXIMUM_VALUE = 21;

    @Override
    public boolean support(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    @Override
    public int calculate(List<Card> cards) {
        int sum = super.calculate(cards);

        if (sum + ACE_WEIGHT <= MAXIMUM_VALUE) {
            return sum + ACE_WEIGHT;
        }
        return sum;
    }
}
