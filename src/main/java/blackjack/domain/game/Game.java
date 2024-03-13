package blackjack.domain.game;

import blackjack.domain.gameresult.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

public class Game {

    private final GameParticipants gameParticipants;
    private final Deck deck;

    private Game(GameParticipants gameParticipants, Deck deck) {
        this.gameParticipants = gameParticipants;
        this.gameParticipants.handOutInitialCards(deck);
        this.deck = deck;
    }

    public static Game of(GameParticipants gameParticipants) {
        return new Game(gameParticipants, Deck.createShuffledDeck());
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

    public Deck getDeck() {
        return deck;
    }
}
