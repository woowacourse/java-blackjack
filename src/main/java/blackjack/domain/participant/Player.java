package blackjack.domain.participant;

import blackjack.domain.card.Cards;

import static blackjack.controller.BlackJackGame.BLACKJACK_NUMBER;

public class Player extends Participant {

    public Player(Nickname nickname) {
        super(nickname, new Cards());
    }

    @Override
    public boolean canDraw() {
        return this.cards.calculateScore() < BLACKJACK_NUMBER;
    }
}
