package blackjack.domain.state;

import blackjack.domain.card.PlayerCards;

public class ParticipantBust extends Bust {

    ParticipantBust(PlayerCards cards) {
        super(cards);
    }
}
