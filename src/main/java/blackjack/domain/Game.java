package blackjack.domain;

import java.util.List;

public class Game {

    private final GameParticipants gameParticipants;
    private final Deck deck;

    private Game(List<String> playerNames, Deck deck) {
        this.gameParticipants = GameParticipants.of(playerNames);
        this.deck = deck;
    }

    public static Game of(List<String> playerNames) {
        Deck deck = Deck.createShuffledDeck();
        Game game = new Game(playerNames, deck);
        return game;
    }

    public GameResult makeGameResult() {
        return GameResult.of(gameParticipants.getDealer(), gameParticipants.getPlayers());
    }

    public Dealer getDealer() {
        return gameParticipants.getDealer();
    }

    public Players getPlayers() {
        return gameParticipants.getPlayers();
    }
}
