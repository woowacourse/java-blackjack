package blackjack.fixture;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Name;
import blackjack.domain.player.Participant;
import blackjack.domain.player.PlayerInfo;

public class ParticipantImpl extends Participant {
    public ParticipantImpl(final Name name, final Cards cards) {
        super(name, cards);
    }

    public ParticipantImpl(final PlayerInfo playerInfo) {
        super(playerInfo);
    }

    @Override
    public boolean isReceivable() {
        throw new UnsupportedOperationException("지원안함");
    }

}
