package domain;

import java.util.List;

public class Dealer {

    public static final int DEALER_HIT_THRESHOLD = 16;
    public static final int ACE_HIGH_LOW_DIFF = 10;
    public static final int BUST_THRESHOLD = 21;
    public static final int BUST_SCORE = 0;

    private final List<Card> cards;

    private Dealer(List<Card> cards) {
        this.cards = cards;
    }

    public static Dealer of(List<Card> cards) {
        return new Dealer(cards);
    }

    public void drawUntilHit(Cards deck) {
        while (isHit()) {
            addCard(deck.draw());
        }
    }

    private boolean isHit() {
        return calculateScore() < DEALER_HIT_THRESHOLD;
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
            cardScore -= ACE_HIGH_LOW_DIFF;
        }
        return cardScore;
    }

    private boolean isBust(int score) {
        return score > BUST_THRESHOLD;
    }

    public int getScoreOrZeroIfBust() {
        int score = calculateScore();
        if (isBust(score)) {
            return BUST_SCORE;
        }
        return score;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

}