package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            return getIncludingAceSum();
        }

        return getExcludingAceSum();
    }

    private int getIncludingAceSum() {
        int aceCount = getAceCount();
        int excludingAceSum = getExcludingAceSum();
        int sum = excludingAceSum + aceCount * ACE_UPPER_SCORE;

        while (sum > BLACKJACK_SYMBOL_SCORE && aceCount > 0) {
            sum -= ACE_SCORE_DIFFERENCE;
            aceCount--;
        }
        return sum;
    }

    private int getExcludingAceSum() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return getAceCount() > 0;
    }

    private int getAceCount() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
