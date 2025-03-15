package blackjack.domain.gamer;

import blackjack.domain.card.Card;

public class Player extends Gamer {

    public Player(String name, Card... cards) {
        super(name, cards);
    }

    @Override
    public boolean canReceiveAdditionalCards() {
        return !isBust();
    }
}
