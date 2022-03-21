package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public class Ready extends Running {

    public Ready(final ParticipantCards participantCards) {
        super(participantCards);
    }

    @Override
    public ParticipantCards participantCards() {
        return participantCards;
    }

    public State draw(final Card card) {
        final ParticipantCards participantCards = this.participantCards.addCard2(card);
        if (participantCards.isReady()) {
            return new Ready(participantCards);
        }
        if (participantCards.isBlackjack()) {
            return new Blackjack(participantCards);
        }
        return new Hit(participantCards);
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException();
    }

}


