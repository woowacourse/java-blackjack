package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

import java.util.List;

public abstract class ParticipantCardsFixture {
    public static ParticipantCards createParticipantsCards(final List<Card> initialCards, final List<Card> cards) {
        ParticipantCards participantCards = new ParticipantCards(initialCards);
        cards.forEach(participantCards::receive);
        return participantCards;
    }
}
