package blackjack.domain.card;

import static blackjack.domain.Rule.DIFFERENCE_IN_ACE_SCORE;
import static blackjack.domain.Rule.WINNING_SCORE;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cards implements Iterable<Card> {

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
            totalScore -= DIFFERENCE_IN_ACE_SCORE.getValue();
        }
        return totalScore;
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int getSize() {
        return cards.size();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
