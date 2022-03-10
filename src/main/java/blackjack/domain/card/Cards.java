package blackjack.domain.card;

import blackjack.util.Constants;
import java.util.ArrayList;
import java.util.List;

public class Cards {
    public static final int ACE_MINUS_NUMBER = 10;
    private final List<Card> cards;

    private Cards() {
        this.cards = new ArrayList<>();
    }

    public static Cards of() {
        return new Cards();
    }


    public void add(final Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int getPoint() {
        return applyAcePoint(getRawPoint(), getAceCount());
    }

    private int applyAcePoint(int point, int aceCount) {
        if (point > Constants.BLACKJACK_NUMBER && aceCount > 0) {
            point -= ACE_MINUS_NUMBER;
            return applyAcePoint(point, aceCount - 1);
        }
        return point;
    }

    private int getRawPoint() {
        return cards.stream()
                .mapToInt(card -> card.getDenomination().getPoint())
                .sum();
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    @Override
    public String toString() {
        return cards.toString().substring(1, cards.toString().length() - 1);
    }
}