package blackjack.domain.card;

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
        int totalPoint = cards.stream()
                .mapToInt(Card::getCardPoint)
                .sum();
        return applyAceAdjustment(totalPoint);
    }

    private int applyAceAdjustment(int totalPoint) {
        int aceCount = getAceCount();

        while (aceCount > 0 && totalPoint > BLACKJACK_POINT) {
            totalPoint -= ACE_OFFSET_POINT;
            aceCount--;
        }

        return totalPoint;
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isBust() {
        return getTotalPoint() > BLACKJACK_POINT;
    }


    public String getFirstCardName() {
        return cards.getFirst().getName();
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && getTotalPoint() == BLACKJACK_POINT;
    }


    public String getCardNames() {
        List<String> names = new ArrayList<>();
        for (Card card : cards) {
            names.add(card.getName());
        }

        return String.join(", ", names);
    }

}
