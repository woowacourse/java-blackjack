package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.result.DealerResult;
import domain.result.PlayerResult;
import domain.result.Results;

public class BlackJackGameRunner {

    private static final int START_DEAL_COUNT = 2;

    private final CardGenerator cardGenerator;
    private final Dealer dealer;
    private final List<Player> players;
    private int playerPassedHitStageCount;
    private Results results;

    private BlackJackGameRunner(final CardGenerator cardGenerator, final Dealer dealer, final List<Player> players) {
        this.cardGenerator = cardGenerator;
        this.dealer = dealer;
        this.players = players;
        this.playerPassedHitStageCount = 0;
    }

    public static BlackJackGameRunner of(
            final CardGenerator cardGenerator,
            final List<String> playerNames,
            final List<Integer> bets
    ) {
        Dealer dealer = new Dealer();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), bets.get(i)));
        }
        return new BlackJackGameRunner(cardGenerator, dealer, players);
    }

    public Dealer giveDealerInitialCards() {
        for (int i = 0; i < START_DEAL_COUNT; i++) {
            giveCard(dealer);
        }
        return dealer;
    }

    public List<Player> givePlayersInitialCards() {
        for (int i = 0; i < START_DEAL_COUNT; i++) {
            players.forEach(this::giveCard);
        }
        return Collections.unmodifiableList(players);
    }

    public boolean isGameOnPlayersHitStage() {
        if (playerPassedHitStageCount >= players.size()) {
            return false;
        }
        if (!getCurrentPlayerOnHitStage().canReceiveCard()) {
            endCurrentPlayerHitStage();
        }
        return playerPassedHitStageCount < players.size();
    }

    public Player getCurrentPlayerOnHitStage() {
        assertGameOnPlayersHitStage();
        return players.get(playerPassedHitStageCount);
    }

    public void handlePlayerHit(final boolean hit) {
        assertGameOnPlayersHitStage();
        Player player = getCurrentPlayerOnHitStage();
        if (hit) {
            giveCard(player);
            return;
        }
        endCurrentPlayerHitStage();
    }

    private void assertGameOnPlayersHitStage() {
        if (playerPassedHitStageCount >= players.size()) {
            throw new IllegalStateException();
        }
    }

    private void endCurrentPlayerHitStage() {
        playerPassedHitStageCount++;
    }

    public boolean giveDealerIfReceivable() {
        if (dealer.canReceiveCard()) {
            giveCard(dealer);
            return true;
        }
        return false;
    }

    private void giveCard(final Player player) {
        player.addCard(cardGenerator.generate());
    }

    public DealerResult getDealerResults() {
        results = Results.of(dealer, players);
        return results.getDealerResult();
    }

    public List<PlayerResult> getPlayerResults() {
        return results.getPlayerResults();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
