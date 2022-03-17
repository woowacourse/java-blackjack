package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cards implements Iterable<Card> {

    public static final int BLACK_JACK_TARGET_SCORE = 21;
    private static final int DIFFERENCE_IN_ACE_SCORE = 10;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int getTotalScore() {
        int totalScore = cards.stream()
                .map(Card::getDenomination)
                .mapToInt(Denomination::getValue)
                .sum();

        for (int i = 0; i < countAce(); i++) {
            totalScore = changeAceScore(totalScore);
        }
        return totalScore;
    }

    private int changeAceScore(int totalScore) {
        if (totalScore > BLACK_JACK_TARGET_SCORE) {
            totalScore -= DIFFERENCE_IN_ACE_SCORE;
        }
        return totalScore;
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
