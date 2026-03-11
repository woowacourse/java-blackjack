package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BUST_CRITERIA = 21;
    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = cards;
    }

    public static Hand of(Card card1, Card card2) {
        return new Hand(List.of(card1, card2));
    }

    public boolean isBust() {
        return calculateCardScoreSum() > BUST_CRITERIA;
    }

    public boolean isFull() {
        return calculateCardScoreSum() == BUST_CRITERIA;
    }

    public List<Card> showCards() {
        return Collections.unmodifiableList(cards);
    }

    public Hand addCard(Card card) {
        ArrayList<Card> mutableCards = new ArrayList<>(cards);
        mutableCards.add(card);
        return new Hand(mutableCards);
    }

    private int calculateCardScoreSum() {
        int sumExceptAce = calculateCardScoreSumExceptAce();
        int sumAce = AceScoreDiscriminator.calculateAceCardsSum(cards, sumExceptAce);

        return sumAce + sumExceptAce;
    }

    private int calculateCardScoreSumExceptAce() {
        int sum = 0;
        for (Card card : cards) {
            sum = calculateCardScoreSumWithoutAce(card, sum);
        }
        return sum;
    }

    private int calculateCardScoreSumWithoutAce(Card card, int sum) {
        if (!card.isAce()) {
            sum += card.getCardContents().getScore();
        }
        return sum;
    }
}