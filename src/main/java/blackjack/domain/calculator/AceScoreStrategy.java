package blackjack.domain.calculator;

import blackjack.domain.card.CardBundle;

class AceScoreStrategy extends DefaultScoreStrategy {

    private static final int ACE_WEIGHT = 10;
    private static final int MAXIMUM_VALUE = 21;

    @Override
    public boolean support(CardBundle cards) {
        return cards.hasAce();
    }

    @Override
    public int calculate(CardBundle cardBundle) {
        int sum = super.calculate(cardBundle);

        if (sum + ACE_WEIGHT <= MAXIMUM_VALUE) {
            return sum + ACE_WEIGHT;
        }
        return sum;
    }
}
