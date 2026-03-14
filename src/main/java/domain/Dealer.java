package domain;

import java.util.List;

public class Dealer {

    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int ACE_HIGH_LOW_DIFF = 10;
    private static final int BUST_THRESHOLD = 21;

    private final List<Card> cards;

    private Dealer(List<Card> cards) {
        this.cards = cards;
    }

    public static Dealer of(List<Card> cards) {
        return new Dealer(cards);
    }

    public boolean isHit() {
        return calculateScore() <= DEALER_HIT_THRESHOLD;
    }

    public void addCard(Card card) {
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
        if (cardScore > BUST_THRESHOLD) {
            return cardScore - ACE_HIGH_LOW_DIFF;
        }

        return cardScore;
    }

    public boolean isBust() {
        return calculateScore() > BUST_THRESHOLD;
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

}