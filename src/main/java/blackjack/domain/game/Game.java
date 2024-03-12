package blackjack.domain.game;

import blackjack.domain.gameresult.Batting;
import blackjack.domain.gameresult.GameResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Players;

import java.util.Map;

public class Game {

    private final GameParticipants gameParticipants;
    private final Deck deck;

    private Game(Map<Name, Batting> playerNameAndBattings, Deck deck) {
        this.gameParticipants = GameParticipants.of(playerNameAndBattings);
        this.gameParticipants.handOutInitialCards(deck);
        this.deck = deck;
    }

    public static Game of(Map<Name, Batting> playerNameAndBattings) {
        return new Game(playerNameAndBattings, Deck.createShuffledDeck());
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
