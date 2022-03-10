package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    private Cards() {
        this.cards = new ArrayList<>();
    }

    public static Cards of() {
        return new Cards();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int getPoint() {
        int point = 0;
        for (Card card : cards) {
            point += card.getDenomination().getPoint();
        }

        int aceCount = getAceCount();

        while (point > 21 && aceCount >= 1) {
            point -= 10;
            aceCount--;
        }

        return point;
    }

    private int getAceCount() {
        return (int) cards.stream()
            .filter(card -> card.getDenomination().equals(Denomination.ACE))
            .count();
    }

    @Override
    public String toString() {
        return cards.toString().substring(1, cards.toString().length() - 1);
    }
}
