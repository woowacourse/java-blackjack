package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.state.Ready;

public class Participant extends AbstractPlayer implements Player {

    public Participant(Name name, Deck deck) {
        this.name = name;
        this.state = Ready.dealToParticipant(deck.pick(), deck.pick());
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name=" + name +
                ", playerCards=" + state.getCards() +
                '}';
    }
}
