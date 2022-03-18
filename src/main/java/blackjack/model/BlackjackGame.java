package blackjack.model;

import blackjack.model.card.CardDeck;
import blackjack.model.participant.Participants;
import java.util.List;

public class BlackjackGame {
    private final Participants participants;
    private final CardDeck cardDeck;

    public BlackjackGame(final List<String> names) {
        this.participants = new Participants(names);
        this.cardDeck = new CardDeck();
    }
}
