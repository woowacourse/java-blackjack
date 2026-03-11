package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BLACKJACK_POINT = 21;
    private static final int ACE_OFFSET_POINT = 10;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCount() {
        return cards.size();
    }

    public int getTotalPoint() {
        int normalPoint = getNormalPoint();
        int aceCount = getAceCount();
        return calculateTotalPoint(aceCount, normalPoint);
    }

    private static int calculateTotalPoint(int aceCount, int totalPoint) {
        while (aceCount > 0 && totalPoint > BLACKJACK_POINT) {
            totalPoint -= ACE_OFFSET_POINT;
            aceCount--;
        }
        return totalPoint;
    }

    private int getNormalPoint() {
        int totalPoint = 0;
        for (Card card : cards) {
            totalPoint += card.getCardPoint();
        }
        return totalPoint;
    }

    private int getAceCount() {
        return (int) cards.stream().filter(Card::isAce).count();
    }

    public boolean isBust() {
        return getTotalPoint() > BLACKJACK_POINT;
    }

    public String getCardNames() {

        List<String> names = new ArrayList<>();
        for (Card card : cards) {
            names.add(card.getName());
        }

        return String.join(", ", names);
    }

}
