package domain.participant;

import domain.card.Cards;

public class Player extends Participant {

    public Player(ParticipantName participantName, Cards cards) {
        super(participantName, cards);
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
