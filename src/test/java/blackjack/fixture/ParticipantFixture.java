package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.game.ParticipantCards;
import blackjack.domain.participant.Participant;

import java.util.List;

public abstract class ParticipantFixture {
    public static Participant create(final Card one, final Card two, final List<Card> cards) {
        ParticipantCards participantsCards = ParticipantCardsFixture.create(one, two, cards);
        return new Participant(participantsCards, "pobi") {
            @Override
            public boolean isHittable() {
                return false;
            }
        };
    }
}
