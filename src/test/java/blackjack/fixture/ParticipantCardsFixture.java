package blackjack.fixture;

import blackjack.domain.Card;
import blackjack.domain.ParticipantCards;

import java.util.List;

public abstract class ParticipantCardsFixture {
    public static ParticipantCards createParticipantsCards(final Card one, final Card two, final List<Card> cards) {
        ParticipantCards participantCards = new ParticipantCards(List.of(one, two));
        cards.forEach(participantCards::receive);
        return participantCards;
    }
}
