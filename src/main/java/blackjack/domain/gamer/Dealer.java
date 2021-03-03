package blackjack.domain.gamer;

import blackjack.domain.card.Cards;

public class Dealer extends Person {

    public Dealer() {
        super.name = "딜러";
        super.cards = new Cards();
    }
}
