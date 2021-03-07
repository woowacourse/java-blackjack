package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public class Hand {
    public static final int MAX_SCORE = 21;
    public static final int ACE_CONVERSION_LIMIT = 11;
    public static final int ACE_DIFFERENCE = 10;
    public static final int BLACK_JACK_CARD_SIZE = 2;

    private final int hitLimit;
    private List<Card> cards;
    private HandStatus status;

    public Hand(List<Card> cards, int hitLimit) {
        this.cards = cards;
        this.hitLimit = hitLimit;
        this.status = calculateStatus(calculateHandScore());
    }

    public void addCard(Card card) {
        cards.add(card);
        status = calculateStatus(calculateHandScore());
    }

    public int calculateHandScore() {
        int score = calculateScore();
        if(score <= ACE_CONVERSION_LIMIT) {
            score += ACE_DIFFERENCE;
        }
        return score;
    }

    private int calculateScore() {
        return cards.stream()
            .mapToInt(card -> card.getDenomination().getScore())
            .sum();
    }

    private HandStatus calculateStatus(int score) {
        if (score == MAX_SCORE && cards.size() == BLACK_JACK_CARD_SIZE) {
            return HandStatus.BLACK_JACK;
        }
        if (score > MAX_SCORE) {
            return HandStatus.BUST;
        }
        if (score > hitLimit) {
            return HandStatus.STAY;
        }
        return HandStatus.HIT;
    }

    public void convertStatusToStay() {
        status = HandStatus.STAY;
    }

    public boolean isHit() {
        return status == HandStatus.HIT;
    }

    public boolean isBust() {
        return status == HandStatus.BUST;
    }

    public boolean isBlackjack() {
        return status == HandStatus.BLACK_JACK;
    }

    public int getScore() {
        return calculateHandScore();
    }

    public HandStatus getStatus() {
        return status;
    }

    public List<Card> getCards() {
        return cards;
    }
}
