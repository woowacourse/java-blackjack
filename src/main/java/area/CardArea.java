package area;

import card.Card;
import card.CardValue;

import java.util.ArrayList;
import java.util.List;

public abstract class CardArea {

    private final List<Card> cards = new ArrayList<>();

    public CardArea(final Card firstCard, final Card secondCard) {
        cards.addAll(List.of(firstCard, secondCard));
    }

    public abstract boolean wantHit();

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int calculate() {
        int total = 0;
        for (final Card card : cards) {
            total += judgeCardValue(total, card.cardValue());
        }
        return total;
    }

    private int judgeCardValue(final Integer totalScore, final CardValue cardValue) {
        int addValue = cardValue.value();
        if (cardValue.isAce()) {
            addValue = judgeAceValue(totalScore);
        }
        return addValue;
    }

    private Integer judgeAceValue(final Integer totalScore) {
        if (totalScore + 11 <= 21) {
            return 11;
        }
        return 1;
    }

    public boolean canMoreCard() {
        return calculate() < 21;
    }
}
