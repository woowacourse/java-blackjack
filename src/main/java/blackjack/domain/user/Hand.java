package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;

public class Hand {
    public static final int ACE_CONVERSION_LIMIT = 11;
    public static final int ACE_DIFFERENCE = 10;

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
        if(score <= ACE_CONVERSION_LIMIT && hasAce()) {
            score += ACE_DIFFERENCE;
        }
        return score;
    }

    private int calculateScore() {
        return cards.stream()
            .mapToInt(card -> card.getDenomination().getScore())
            .sum();
    }

    private boolean hasAce() {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    private HandStatus calculateStatus(int score) {
        return HandStatus.calculateStatus(score, hitLimit, cards.size());
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
