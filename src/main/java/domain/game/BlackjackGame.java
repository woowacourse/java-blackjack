package domain.game;

import domain.strategy.ShuffleStrategy;
import domain.user.GameParticipant;
import domain.user.Player;

import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private static final int START_HIT_COUNT = 2;

    private final Deck deck;
    private final GameParticipant gameParticipant;

    public BlackjackGame(List<String> playerNames, String dealerName, ShuffleStrategy shuffleStrategy) {
        this.deck = new Deck(shuffleStrategy);
        this.gameParticipant = new GameParticipant(playerNames, dealerName);
    }

    public void letDealerHitUntilThreshold() {
        gameParticipant.letDealerHitUntilThreshold(deck);
    }

    public boolean dealerNeedsHit() {
        return gameParticipant.dealerNeedsHit();
    }

    public void startHit() {
        for (int i = 0; i < START_HIT_COUNT; i++) {
            gameParticipant.letPlayersToHit(deck);
        }
    }

    public boolean isBurst(String playerName) {
        return gameParticipant.isBurst(playerName);
    }

    public void hitFor(String playerName) {
        gameParticipant.letPlayerToHit(playerName, deck);
    }

    public Map<Player, GameResult> getGameResultForAllPlayer() {
        return gameParticipant.makeGameResultForAllPlayer();
    }

    public Map<GameResult, Integer> getDealerRecord() {
        return gameParticipant.getDealerRecord(getGameResultForAllPlayer());
    }

    public GameParticipant getGameParticipant() {
        return gameParticipant;
    }
}
