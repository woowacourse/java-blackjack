package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.Participant;
import blackjack.domain.card.ParticipantCards;

import java.util.List;

public abstract class ParticipantFixture {
    public static Participant create(final List<Card> initialCards, final List<Card> cards) {
        ParticipantCards participantsCards = ParticipantCardsFixture.createParticipantsCards(initialCards, cards);
        return new Participant(participantsCards, "pobi") {
            @Override
            public List<Card> initialOpen() {
                return initialCards;
            }

            @Override
            protected boolean isHittable() {
                return false;
            }
        };
    }
}
