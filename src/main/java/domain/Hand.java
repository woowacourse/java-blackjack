package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BUST_CRITERIA = 21;
    private final List<Card> cards;

    private Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Hand of(Card card1, Card card2) {
        return new Hand(List.of(card1, card2));
    }

    //TODO : 책임 : 가지고 있는 카드패가 isBust 인지 판단하세요.
    public boolean isBust() {
        return calculateCardScoreSum() > BUST_CRITERIA;
    }

    private int calculateCardScoreSum() {
        int sumExceptAce = calculateCardScoreSumExceptAce();
        int sumAce = AceScoreDiscriminator.calculateAceCardsSum(cards, sumExceptAce);

        return sumAce + sumExceptAce;
    }

    private int calculateCardScoreSumExceptAce() {
        int sum = 0;
        for (Card card : cards) {
            sum = addCardScoreExceptAce(card, sum);
        }
        return sum;
    }

    private int addCardScoreExceptAce(Card card, int sum) {
        if (!card.isAce()) {
            sum += card.getCardContents().getScore();
        }
        return sum;
    }

//    public void addCard(Card card) {
//        this.cards.add(card);
//    }
}