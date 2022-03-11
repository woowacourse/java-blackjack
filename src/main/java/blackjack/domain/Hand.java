package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_CARD_SIZE = 2;
    private static final int BLACKJACK_SYMBOL_SCORE = 21;
    private static final int ACE_UPPER_SCORE = 11;
    private static final int ACE_LOWER_SCORE = 1;
    private static final int ACE_SCORE_DIFFERENCE = ACE_UPPER_SCORE - ACE_LOWER_SCORE;

    private List<Card> cards = new ArrayList<>();

    public void add(Card... cards) {
        this.cards.addAll(List.of(cards));
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        if (hasAce()) {
            return getSumIncludingAce();
        }

        return getCardScoreSum();
    }

    private int getSumIncludingAce() {
        int countAce = getNumberOfAce();
        int sumExcludingAce = getCardScoreSum();

        for (int i = 0; i < countAce; i++) {
            int sum = sumExcludingAce + (countAce * ACE_UPPER_SCORE) - i * ACE_SCORE_DIFFERENCE;
            if (sum <= BLACKJACK_SYMBOL_SCORE) {
                return sum;
            }
        }

        return sumExcludingAce + countAce;
    }

    private int getCardScoreSum() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return getNumberOfAce() > 0;
    }

    private int getNumberOfAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_SYMBOL_SCORE;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_SIZE && getScore() == BLACKJACK_SYMBOL_SCORE;
    }

    public MatchResult compareMatchResult(Hand opponentCardHand) {
        return MatchResult.get(this, opponentCardHand);
    }
}
