package blackjack.domain.card;

import static blackjack.domain.Rule.WINNING_SCORE;

import blackjack.domain.Rule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cards implements Iterable<Card> {

    private static final int DIFFERENCE_IN_ACE_SCORE = 11 - 1;
    private static final int BLACKJACK_CARDS_SIZE = 2;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int calculateTotalScore() {
        int totalScore = cards.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getValue)
                .sum();

        for (int i = 0; i < countAce(); i++) {
            totalScore = changeAceScore(totalScore);
        }
        return totalScore;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int changeAceScore(int totalScore) {
        if (totalScore > WINNING_SCORE.getValue()) {
            totalScore -= DIFFERENCE_IN_ACE_SCORE;
        }
        return totalScore;
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return calculateTotalScore() > Rule.WINNING_SCORE.getValue();
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARDS_SIZE &&
                calculateTotalScore() == Rule.WINNING_SCORE.getValue();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
