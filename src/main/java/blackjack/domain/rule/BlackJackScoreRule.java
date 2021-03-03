package blackjack.domain.rule;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackScoreRule implements ScoreRule {
    private static final int BOUND_ADDING_ACE = 11;
    private static final int ACE_ADDITIONAL_VALUE = 10;

    @Override
    public int sumTotalScore(List<Card> cards) {
        List<Card> aces = cards.stream()
                .filter(Card::isAce)
                .collect(Collectors.toList());
        int sum = calculateSum(cards);
        return calculateAce(sum, aces.size());
    }

    private int calculateAce(int sum, int size) {
        while (sum <= BOUND_ADDING_ACE && size > 0) {
            sum += ACE_ADDITIONAL_VALUE;
            size--;
        }

        return sum;
    }

    private int calculateSum(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }
}
