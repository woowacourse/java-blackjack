package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.Participant;
import blackjack.domain.card.ParticipantCards;

import java.util.List;

public abstract class ParticipantFixture {
    public static Participant create(final Card one, final Card two, final List<Card> cards) {
        ParticipantCards participantsCards = ParticipantCardsFixture.createParticipantsCards(one, two, cards);
        return new Participant(participantsCards, "pobi") {
            @Override
            protected boolean isHittable() {
                return false;
            }
        };
    }
}
