package blackjack.card.domain.score;

import blackjack.card.domain.Card;

import java.util.List;

public class AceScoreStrategy implements ScoreStrategy {

    private static final int ACE_WEIGHT = 10;
    private static final int BLACKJACK_VALUE = 21;

    @Override
    public boolean support(List<Card> cards) {
        return hasAce(cards) && calculate(cards) <= BLACKJACK_VALUE;
    }

    private boolean hasAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    @Override
    public int calculate(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum() + ACE_WEIGHT;
    }
}
