package domain;

import java.util.List;

public class Player extends Participant {

    public Player(ParticipantName participantName, Cards cards) {
        super(participantName, cards);
    }

    @Override
    public List<Card> getInitialCards() {
        return cards.getCards();
    }
}
