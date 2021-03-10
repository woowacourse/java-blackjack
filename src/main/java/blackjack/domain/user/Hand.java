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
        this.status = calculateStatus();
    }

    public void addCard(Card card) {
        cards.add(card);
        status = calculateStatus();
    }

    private int calculateHandScore() {
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

    private HandStatus calculateStatus() {
        int score = calculateHandScore();
        return HandStatus.calculateStatus(score, hitLimit, cards.size());
    }

    public void convertStatusToStay() {
        status = HandStatus.STAY;
    }

    public boolean isHit() {
        return status.isHit();
    }

    public boolean isBust() {
        return status.isBust();
    }

    public boolean isBlackjack() {
        return status.isBlackjack();
    }

    public int getScore() {
        return calculateHandScore();
    }

    public List<Card> getCards() {
        return cards;
    }
}
