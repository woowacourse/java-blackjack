package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class FinalCards {

    private final List<Card> cards;
    private final int sum;

    // TODO final 일관성 있게 수정
    public FinalCards(final List<Card> cards, final int sum) {
        this.cards = cards;
        this.sum = sum;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public int getSum() {
        return sum;
    }
}
