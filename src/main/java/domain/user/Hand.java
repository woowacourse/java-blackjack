package domain.user;

import domain.card.Card;
import domain.card.Number;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BLACK_JACK_CONDITION = 21;
    public static final int FIRST_INDEX = 0;
    protected final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int sumCard() {
        int sum = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();
        return addSumByAce(sum);
    }

    private int addSumByAce(int sum) {
        if (hasAce()) {
            return Number.sumContainingSoftAce(sum);
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean busted() {
        return sumCard() > BLACK_JACK_CONDITION;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirstCard() {
        return cards.get(FIRST_INDEX);
    }
}
