package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int BLACK_JACK_SCORE = 21;
    private static final int ACE_OFFSET = 10;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getSumOfScores() {
        int sumOfScores = cards.stream()
            .map(Card::getScore)
            .reduce(0, Integer::sum);
        if (isContainAce() && sumOfScores + ACE_OFFSET <= BLACK_JACK_SCORE) {
            return sumOfScores + Cards.ACE_OFFSET;
        }
        return sumOfScores;
    }

    private boolean isContainAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isUnder(int score) {
        return getSumOfScores() < score;
    }
}
