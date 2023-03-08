package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Score getSumOfScores() {
        Score sumScore = cards.stream().map(Card::getScore).reduce((a, b) -> a.add(b)).get();
        if (isContainAce() && sumScore.isAddableAceOffSet()) {
            return sumScore.addAceOffSet();
        }
        return sumScore;
    }

    private boolean isContainAce() {
        return cards.stream().anyMatch(card2 -> card2.getDenomination() == Denomination.ACE);
    }

    public boolean isUnder(Score other) {
        return getSumOfScores().isLessThan(other);
    }

    public boolean isBust() {
        return getSumOfScores().isBust();
    }

    public boolean isAddable() {
        return getSumOfScores().isAddable();
    }
}
