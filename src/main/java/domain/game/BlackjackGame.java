package domain.game;

import domain.strategy.ShuffleStrategy;
import domain.user.GameParticipant;
import domain.user.Player;

import java.util.List;

public class BlackjackGame {

    private static final int START_HIT_COUNT = 2;

    private final Deck deck;
    private final GameParticipant gameParticipant;

    public BlackjackGame(
            final List<String> playerNames,
            final ShuffleStrategy shuffleStrategy
    ) {
        this.deck = new Deck(shuffleStrategy);
        this.gameParticipant = new GameParticipant(playerNames);
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

    public void hitFor(final Player player) {
        player.draw(deck.serve());
    }

    public void updateBetAmount() {
        gameParticipant.updateBetAmountByGameResult();
    }

    public GameParticipant getGameParticipant() {
        return gameParticipant;
    }
}
