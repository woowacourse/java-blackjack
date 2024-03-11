package domain.cards.gamercards;

import domain.cards.Card;
import java.util.Collections;
import java.util.List;

public class PlayerCards {

    private static final int BUST_THRESHOLD = 21;

    protected final List<Card> cards;

    public PlayerCards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int scoreExceptAce = sumExceptAceCards();
        int scoreWithAce = sumAceCards(scoreExceptAce);
        return scoreExceptAce + scoreWithAce;
    }

    private int sumExceptAceCards() {
        return cards.stream()
                .filter(Card::isNotAce)
                .mapToInt(Card::score)
                .sum();
    }

    private int sumAceCards(int score) {
        int acesScore = 0;
        for (Card aceCard : filterAceCards()) {
            int aceScore = aceCard.decideAceScore(score);
            score += aceScore;
            acesScore += aceScore;
        }
        return acesScore;
    }

    private List<Card> filterAceCards() {
        return cards.stream()
                .filter(Card::isAce)
                .toList();
    }

    public boolean hasScoreUnderBustThreshold() {
        return calculateScore() <= BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
