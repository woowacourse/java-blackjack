package blackjack.fixture;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Name;
import blackjack.domain.player.Participant;

public class ParticipantImpl extends Participant {
    public ParticipantImpl(final Name name, final Cards cards) {
        super(name, cards);
    }

    @Override
    public boolean isReceivable() {
        throw new UnsupportedOperationException("지원안함");
    }

}
