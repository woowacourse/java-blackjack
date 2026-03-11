package domain;

import java.util.List;

public class Dealer {

    private final List<Card> cards;

    private Dealer(List<Card> cards) {
        this.cards = cards;
    }

    public static Dealer of(List<Card> cards) {
        return new Dealer(cards);
    }

    public int drawUntilHitAndReturnCount(Cards deck) {
        int count = 0;
        while (isHit()) {
            addCard(deck.draw());
            count++;
        }
        return count;
    }

    private boolean isHit() {
        return calculateScore() < Policy.DEALER_HIT_THRESHOLD;
    }

    private void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int cardScore = calculateRawScore();
        int aceCount = countAce();

        for (int i = 0; i < aceCount; i++) {
            cardScore = adjustForAce(cardScore);
        }

        return cardScore;
    }

    private int calculateRawScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    private int adjustForAce(int cardScore) {
        if (isBust(cardScore)) {
            cardScore -= Policy.ACE_HIGH_LOW_DIFF;
        }
        return cardScore;
    }

    public boolean isBust(int cardScore) {
        return cardScore > Policy.BUST_THRESHOLD;
    }

    public int getScoreOrZeroIfBust() {
        int score = calculateScore();
        if (isBust(score)) {
            return Policy.BUST_SCORE;
        }
        return score;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

}