package blackjack.domain.supplies;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int INIT_COUNT = 2;

    private final List<Card> myCards;

    public Hand() {
        this.myCards = new ArrayList<>();
    }

    public void add(List<Card> cards) {
        myCards.addAll(cards);
    }

    public void add(Card card) {
        myCards.add(card);
    }

    public Score score() {
        Score totalScore = totalScore();

        if (hasAce()) {
            return totalScore.addAceScoreIfNotBust();
        }
        return totalScore;
    }

    private Score totalScore() {
        return new Score(myCards.stream()
                .mapToInt(Card::score)
                .sum());
    }

    private boolean hasAce() {
        return myCards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return score().isMaxScore() && getNumberOfCards() == INIT_COUNT;
    }

    public boolean isBust() {
        return score().isBust();
    }

    public boolean isMaxScore() {
        return score().isMaxScore();
    }

    public int getNumberOfCards() {
        return myCards.size();
    }

    public List<Card> getMyCards() {
        return myCards;
    }

    public Card getMyCardAt(int index) {
        return myCards.get(index);
    }
}
