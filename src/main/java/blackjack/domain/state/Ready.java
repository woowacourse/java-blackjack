package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public class Ready extends Running {

    private static final String READY_STAY_ERROR_MESSAGE = "[ERROR] 처음 카드는 두장 미만으로 받을 수 없습니다.";

    public Ready(ParticipantCards participantCards) {
        super(participantCards);
    }

    public State draw(final Card card) {
        ParticipantCards participantCards = this.participantCards.addCard(card);

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
        throw new IllegalStateException(READY_STAY_ERROR_MESSAGE);
    }

}


