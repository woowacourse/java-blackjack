package domain;

import java.util.List;

public class Game {
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

}
