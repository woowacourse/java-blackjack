package domain.player;

import domain.card.Card;
import domain.card.CardCalculator;
import domain.money.Money;

import java.util.List;

public class Dealer extends User {
    private static final int ADDITIONAL_INSERT_CARD_STANDARD = 16;

    public Dealer(List<Card> userCardDeck) {
        super(userCardDeck);
        this.name = "딜러";
        this.money = new Money(0);
    }

    public String startCardReport() {
        return String.format("%s: %s", this.name, cardToString().substring(0, cardToString().indexOf(",")));
    }

    @Override
    public boolean isDrawCard() {
        return CardCalculator.sumCardDeck(this.cards) <= ADDITIONAL_INSERT_CARD_STANDARD;
    }
}
