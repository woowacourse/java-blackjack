package blackjack.fixture;

import blackjack.domain.Card;
import blackjack.domain.Participant;
import blackjack.domain.ParticipantCards;

import java.util.Collections;
import java.util.List;

public abstract class ParticipantFixture {
    public static Participant create(final Card one, final Card two, final List<Card> cards) {
        ParticipantCards participantsCards = ParticipantCardsFixture.createParticipantsCards(one, two, cards);
        return new Participant(participantsCards) {
            @Override
            protected void hit(final Card card) {
                /* NOT IMPLEMENTED */
            }

            @Override
            protected List<Card> open(final int cardCount) {
                return Collections.emptyList();
            }

            @Override
            protected boolean isHittable() {
                return false;
            }
        };
    }
}
