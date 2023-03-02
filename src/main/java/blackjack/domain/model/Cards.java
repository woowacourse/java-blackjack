package blackjack.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int calculateTotalScore() {
        return this.cards.stream().
                map(Card::getValue).
                reduce(0,Integer::sum);
    }
    public Card getFirstCard(){
        return this.cards.get(0);
    }
}
