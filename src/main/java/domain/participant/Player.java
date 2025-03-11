package domain.participant;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public class Player extends Participant {

    public Player(ParticipantName participantName, Cards cards) {
        super(participantName, cards);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public List<Card> getInitialCards() {
        return cards.getCards();
    }
}
