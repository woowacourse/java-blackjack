package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Status;

public class Player extends Participant {

    public Player(Name name, Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean isFinished() {
        return cards.getStatus() != Status.NONE;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
