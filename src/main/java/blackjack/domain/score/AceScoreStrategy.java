package blackjack.domain.score;

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

    private int getAceCount(List<Card> cards) {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    @Override
    public int calculate(List<Card> cards) {
        int aceCount = getAceCount(cards);
        int sum = super.calculate(cards);

        while (aceCount > 0 && sum + ACE_WEIGHT <= MAXIMUM_VALUE) {
            sum += ACE_WEIGHT;
            aceCount--;
        }
        return sum;
    }
}
