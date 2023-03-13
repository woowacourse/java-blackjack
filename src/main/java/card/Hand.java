package card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int ACE_BONUS_POINT = 10;
    public static final int ABLE_TO_ADD_BONUS_POINT = 11;
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        if (cards == null) {
            cards = new ArrayList<>();
        }
        this.cards = cards;
    }

    public Hand addCard(Card card) {
        ArrayList<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);
        return new Hand(newCards);
    }

    public int getPoint() {
        int totalPoint = 0;
        for (Card card : cards) {
            totalPoint += card.getPoint();
        }
        if (hasAce() && totalPoint <= ABLE_TO_ADD_BONUS_POINT) {
            totalPoint += ACE_BONUS_POINT;
        }
        return totalPoint;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(card -> card.getDenomination() == Denomination.ACE);
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
