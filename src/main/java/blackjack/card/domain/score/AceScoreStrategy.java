package blackjack.card.domain.score;

import blackjack.card.domain.Card;

import java.util.List;

public class AceScoreStrategy implements ScoreStrategy {

    private static final int BIG_ACE_VALUE = 11;
    private static final int ACE_WEIGHT = 10;
    private static final int MAXIMUM_VALUE = 21;

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
                .mapToInt(this::convertScore)
                .sum();

        int aceCount = getAceCount(cards);

        while (sum > MAXIMUM_VALUE && aceCount > 0) {
            sum -= ACE_WEIGHT;
            aceCount--;
        }
        return sum;
    }

    private int convertScore(Card card) {
        if (card.isAce()) {
            return BIG_ACE_VALUE;
        }
        return card.getNumber();
    }
}
