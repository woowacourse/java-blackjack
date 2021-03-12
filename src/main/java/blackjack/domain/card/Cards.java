package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    public static final int BLACK_JACK_SCORE = 21;
    public static final int ACE_PIVOT = 11;
    public static final int ACE_CONVERSION = 10;
    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getList() {
        return new ArrayList<>(cards);
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public int getScore() {
        boolean hasAce = hasAce();
        int sum = cards.stream().mapToInt(Card::getFaceValue).sum();

        if (sum <= ACE_PIVOT && hasAce) {
            return sum + ACE_CONVERSION;
        }
        return sum;
    }

    public boolean isBust() {
        return getScore() > BLACK_JACK_SCORE;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && getScore() == 21;
    }
}
