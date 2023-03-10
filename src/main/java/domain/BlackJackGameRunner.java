package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGameRunner {

    private static final int START_DEAL_COUNT = 2;

    private final CardGenerator cardGenerator;
    private final Dealer dealer;
    private final List<Player> players;
    private int playerPassedHitStageCount;

    private BlackJackGameRunner(final CardGenerator cardGenerator, final Dealer dealer, final List<Player> players) {
        this.cardGenerator = cardGenerator;
        this.dealer = dealer;
        this.players = players;
        this.playerPassedHitStageCount = 0;
    }

    public static BlackJackGameRunner of(final CardGenerator cardGenerator, final List<String> playerNames) {
        Dealer dealer = new Dealer();
        List<Player> players = playerNames.stream()
            .map(Player::from)
            .collect(Collectors.toList());
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

    public void handlePlayerHit(boolean hit) {
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

    public Result makeDealerResult() {
        final List<Score> scores = players.stream()
            .map(Player::getScore)
            .collect(Collectors.toList());
        return Result.decide(dealer.getScore(), scores);
    }

    public Map<Player, Result> makePlayersResult() {
        Map<Player, Result> results = new LinkedHashMap<>();
        for (Player player : players) {
            results.put(player, Result.decide(player.getScore(), dealer.getScore()));
        }
        return results;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
