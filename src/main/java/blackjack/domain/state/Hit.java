package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public class Hit extends Running {

    public Hit(ParticipantCards participantCards) {
        super(participantCards);
    }

    @Override
    public ParticipantCards participantCards() {
        return participantCards;
    }

    @Override
    public State draw(Card card) {
        ParticipantCards participantCards = this.participantCards.addCard2(card);

        if (participantCards.isBust()) {
            return new Bust(participantCards);
        }
        return new Hit(participantCards);
    }

    @Override
    public State stay() {
        return new Stay(participantCards);
    }

}
