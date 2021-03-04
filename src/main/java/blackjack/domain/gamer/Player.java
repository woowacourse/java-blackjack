package blackjack.domain.gamer;

import blackjack.domain.card.Cards;

import static blackjack.controller.BlackJackGame.BLACKJACK_NUMBER;

public class Player extends Person {

    public Player(Name name) {
        super.name = name;
        super.cards = new Cards();
    }

    @Override
    public boolean canDraw() {
        return this.cards.calculateScore() < BLACKJACK_NUMBER;
    }
}
