package blackjack.card.domain.score;

import blackjack.card.domain.Card;

import java.util.List;

public class AceScoreStrategy implements ScoreStrategy {

    private static final int ACE_WEIGHT = 10;
    private static final int BLACKJACK_VALUE = 21;

    @Override
    public boolean support(List<Card> cards) {
        return getAceCount(cards) > 0;
    }

    private int getAceCount(List<Card> cards) {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    @Override
    public int calculate(List<Card> cards) {
        int sum = cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
        int aceCount = getAceCount(cards);

        while (canAddAceWeight(sum, aceCount)) {
            sum += ACE_WEIGHT;
            aceCount--;
        }
        return sum;
    }

    private boolean canAddAceWeight(int sum, int aceCount) {
        return isNotBurst(sum) && aceCount > 0;
    }

    private boolean isNotBurst(int sum) {
        return sum + ACE_WEIGHT <= BLACKJACK_VALUE;
    }
}
