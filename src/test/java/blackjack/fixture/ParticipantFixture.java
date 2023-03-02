package blackjack.fixture;

import blackjack.domain.Card;
import blackjack.domain.Participant;
import blackjack.domain.ParticipantCards;

import java.util.List;

public abstract class ParticipantFixture {
    public static Participant create(final List<Card> cards) {
        ParticipantCards participantsCards = ParticipantCardsFixture.createParticipantsCards(cards);
        return new Participant(participantsCards) {};
    }
}
