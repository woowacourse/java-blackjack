package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public abstract class Running extends Started{

    public Running(ParticipantCards participantCards) {
        super(participantCards);
    }

    @Override
    public ParticipantCards participantCards() {
        return participantCards;
    }

    @Override
    abstract public State draw(Card card);

    public boolean isFinished() {
        return false;
    }

}
