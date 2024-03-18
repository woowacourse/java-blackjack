package blackjack.fixture;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Participant;

public class ParticipantImpl extends Participant {
    @Override
    public String getNameAsString() {
        return null;
    }

    public ParticipantImpl(final Cards cards) {
        super(cards);
    }

    public ParticipantImpl() {
        super();
    }

    @Override
    public boolean isReceivable() {
        throw new UnsupportedOperationException("지원안함");
    }

}
