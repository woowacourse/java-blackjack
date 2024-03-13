package model.dealer;

import java.util.List;
import model.card.Card;
import model.card.Cards;

public class Dealer {

    private static final String NAME = "딜러";
    private static final int HIT_CONDITION = 17;

    private Cards cards;

    public Dealer() {
        this(new Cards(List.of()));
    }

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public boolean isPossibleHit() {
        int totalNumbers = cards.calculateTotalNumbers();
        return totalNumbers < HIT_CONDITION;
    }

    public void hitCard(Card card) {
        cards = cards.add(card);
    }

    public Card getFirstCard() {
        return cards.getCards().get(0);
    }

    public int cardsSize() {
        return cards.size();
    }

    public Cards getCards() {
        return cards;
    }

    public String getName() {
        return NAME;
    }
}
