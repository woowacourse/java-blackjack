package domain;

import java.util.List;
import java.util.Map;

public class Game {
    private static final String DEALER = "딜러";

    private final Deck totalDeck;
    private final Participants participants;

    public Game(Deck totalDeck, Participants participants) {
        this.totalDeck = totalDeck;
        this.participants = participants;
    }

    public static Game ready(List<String> playerNames, CardCreationStrategy strategy) {
        Deck totalDeck = Deck.createDeck(strategy);
        Participants participants = Participants.of(playerNames, totalDeck);
        return new Game(totalDeck, participants);
    }

    public Map<String, List<Card>> showInitialCardShareResult() {
        Map<String, List<Card>> result = participants.getDecksPerUser();
        List<Card> dealerCards = result.get(DEALER);
        dealerCards.removeLast();
        return participants.getDecksPerUser();
    }
}
