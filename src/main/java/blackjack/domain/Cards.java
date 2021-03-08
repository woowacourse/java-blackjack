package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int ACE_BONUS = 10;
    public static final int HIGHEST_POINT = 21;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    private boolean findAce(List<Card> cards) {
        for (Card card : cards) {
            if (card.isAce()) {
                return true;
            }
        }
        return false;
    }

    public int getPoint(int maxPoint) {
        Integer cardValue = cards.stream()
            .map(Card::givePoint)
            .reduce(0, Integer::sum);
        if (findAce(cards) && cardValue + ACE_BONUS <= maxPoint) {
            return cardValue + ACE_BONUS;
        }
        return cardValue;
    }

    public int size() {
        return cards.size();
    }

    public Card get(int i) {
        return cards.get(i);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
