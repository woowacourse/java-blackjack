package blackjack.domain.human;

import static blackjack.util.Constants.BLACKJACK_NUMBER;

import blackjack.domain.card.Cards;

public class Player extends Human {

    private Player(String name) {
        super(Cards.of(), name);
    }

    public static Player of(String name) {
        return new Player(name);
    }

    @Override
    public boolean isAbleToHit() {
        return cards.getPoint() < BLACKJACK_NUMBER;
    }
}