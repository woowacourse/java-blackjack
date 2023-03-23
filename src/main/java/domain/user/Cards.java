package domain.user;

import domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int MAX_SCORE = 21;
    private static final int ACE_OFFSET = 10;
    public static final int CARD_INITIALIZED_SIZE = 2;

    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

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
        return addAceOffset(sumOfScores);
    }

    private int addAceOffset(int sumOfScores) {
        if (isContainAce() && sumOfScores + ACE_OFFSET <= MAX_SCORE) {
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

    public boolean isBust() {
        return getSumOfScores() > MAX_SCORE;
    }

    public boolean isMaxScore() {
        return getSumOfScores() == MAX_SCORE;
    }

    public boolean isInitCompleted() {
        return cards.size() >= CARD_INITIALIZED_SIZE;
    }
}
