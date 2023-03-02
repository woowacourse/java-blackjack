package blackjack.domain;

import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Deck deck;
    private final Participants participants;

    public BlackJackGame(DeckGenerator deckGenerator, List<String> playerNames) {
        this.deck = deckGenerator.generate();
        this.participants = Participants.of(playerNames);
    }

    public void handOut() {
        participants.handOut(deck);
    }

    public Map<String, List<Card>> openCards() {
        return participants.openCards();
    }
}
