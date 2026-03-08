package domain;

import java.util.List;

public class Player extends Participant {

    public Player(Deck participantDeck, String name) {
        super(participantDeck, name);
    }

    @Override
    public List<Card> getInitialVisibleCards() {
        return super.getDeck().getCards();
    }

}
