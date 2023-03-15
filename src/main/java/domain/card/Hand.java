package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(final Card card) {
        cards.add(card);
    }

    public Score getSumOfScores() {
        Score sumScore = cards.stream().map(Card::getScore).reduce((a, b) -> a.add(b)).get();
        if (isContainAce() && sumScore.isAddableAceOffSet()) {
            return sumScore.addAceOffSet();
        }
        return sumScore;
    }

    private boolean isContainAce() {
        return cards.stream().anyMatch(card -> card.getDenomination() == Denomination.ACE);
    }

    public boolean isUnder(final Score other) {
        return getSumOfScores().isLessThan(other);
    }

    public boolean isAddable() {
        return getSumOfScores().isAddable();
    }

    public boolean isBlackjack() {
        return getSumOfScores().isBlackjackScore() && cards.size() == 2;
    }

    public Card get(final int index) {
        return cards.get(index);
    }

    public List<String> getCardNames() {
        return cards.stream().map(Card::getCardName).collect(Collectors.toUnmodifiableList());
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
