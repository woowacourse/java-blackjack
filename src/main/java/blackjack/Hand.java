package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCount() {
        return cards.size();
    }

    public int getTotalPoint() {
        int totalPoint = 0;
        for(Card card : cards) {
            totalPoint += card.getCardPoint();
        }
        return totalPoint;
    }
}
