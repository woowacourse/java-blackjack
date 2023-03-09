package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.game.ParticipantCards;

import java.util.ArrayList;
import java.util.List;

public abstract class ParticipantCardsFixture {
    public static ParticipantCards create(final Card one, final Card two, final List<Card> cards) {
        final List<Card> newCards = new ArrayList<>();
        newCards.add(one);
        newCards.add(two);

        final Deck deck = DeckMock.create(newCards);
        final ParticipantCards participantCards = new ParticipantCards(deck);
        cards.forEach(participantCards::receive);

        return participantCards;
    }

    public static ParticipantCards create(final Deck deck, final List<Card> cards) {
        ParticipantCards participantCards = new ParticipantCards(deck);
        cards.forEach(participantCards::receive);
        return participantCards;
    }
}
