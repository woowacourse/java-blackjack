package domain.cards;

import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int BUST_THRESHOLD = 21;
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int FIRST_CARD_INDEX = 0;

    private final List<Card> cards;

    public Hand(List<Card> cards) {
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

    public boolean hasScoreUnderHitThreshold() {
        return calculateScore() <= DEALER_HIT_THRESHOLD;
    }

    public Card pickFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
