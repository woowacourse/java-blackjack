package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BLACKJACK_POINT = 21;
    private static final int ACE_OFFSET_POINT = 10;
    private static final int DEALER_STAND_POINT = 17;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCount() {
        return cards.size();
    }

    public int getTotalPoint() {
        int totalPoint = 0;
        for (Card card : cards) {
            totalPoint += card.getCardPoint();
        }
        int aceCount = getAceCount();
        while (aceCount > 0 && totalPoint > BLACKJACK_POINT) {
            totalPoint -= ACE_OFFSET_POINT;
            aceCount--;
        }

        return totalPoint;
    }

    private int getAceCount() {
        int aceCount = 0;
        for (Card card : cards) {
            if (card.isAce()) {
                aceCount += 1;
            }
        }
        return aceCount;
    }

    public boolean isBust() {
        return getTotalPoint() > BLACKJACK_POINT;
    }

    public boolean isOver17() {
        return getTotalPoint() >= DEALER_STAND_POINT;
    }

    public String getFirstCardName() {
        return cards.getFirst().getName();
    }

    public String getCardNames() {

        List<String> names = new ArrayList<>();
        for (Card card : cards) {
            names.add(card.getName());
        }

        return String.join(", ", names);
    }

}
