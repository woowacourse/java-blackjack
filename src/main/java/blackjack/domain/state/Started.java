package blackjack.domain.state;

import blackjack.domain.card.ParticipantCards;

public abstract class Started implements State{

    protected ParticipantCards participantCards;

    public Started(ParticipantCards participantCards) {
        this.participantCards = participantCards;
    }

    @Override
    public ParticipantCards participantCards() {
        return participantCards;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

}
