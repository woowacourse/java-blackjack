package blackjack.fixture;

import blackjack.domain.Card;
import blackjack.domain.ParticipantCards;

import java.util.*;

public abstract class ParticipantCardsFixture {
    public static ParticipantCards createParticipantsCards(final List<Card> cards) {
        return new ParticipantCards(cards);
    }
}
