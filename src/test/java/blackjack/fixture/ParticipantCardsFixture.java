package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;

public abstract class ParticipantCardsFixture {
    public static Cards createParticipantsCards(final List<Card> initialCards, final List<Card> cards) {
        Cards participantCards = new Cards(initialCards);
        cards.forEach(participantCards::receive);
        return participantCards;
    }
}
