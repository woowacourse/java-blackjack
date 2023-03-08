package domain.user;

import domain.game.Deck;
import domain.game.GameResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameParticipant {

    private final List<Player> players;
    private final Dealer dealer;

    public GameParticipant(final List<String> playerNames) {
        this.players = makePlayersWithName(playerNames);
        this.dealer = makeDealer();
    }

    private List<Player> makePlayersWithName(final List<String> playerNames) {
        return playerNames.stream()
                .map(playerName -> new Player(playerName, new CardPool(Collections.emptyList())))
                .collect(Collectors.toList());
    }

    private Dealer makeDealer() {
        return new Dealer(new CardPool(Collections.emptyList()));
    }

    public void letPlayersToHit(final Deck deck) {
        for (Player player : players) {
            player.draw(deck.serve());
        }
        dealer.draw(deck.serve());
    }

    public void letPlayerToHit(final Player player, final Deck deck) {
        player.draw(deck.serve());
    }

    public void letDealerHitUntilThreshold(final Deck deck) {
        while (dealer.needsHit()) {
            dealer.draw(deck.serve());
        }
    }

    public Map<Player, GameResult> makeGameResultForAllPlayer() {
        Map<Player, GameResult> record = new HashMap<>();
        recordGameResult(record);

        return record;
    }

    private void recordGameResult(final Map<Player, GameResult> record) {
        players.forEach(player -> record.put(player, GameResult.makePlayerRecord(player, dealer)));
    }

    public Map<GameResult, Integer> getDealerRecord(final Map<Player, GameResult> record) {
        return GameResult.makeDealerRecord(record);
    }

    public boolean isBurst(final Player player) {
        return player.isBurst();
    }

    public boolean dealerNeedsHit() {
        return dealer.needsHit();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
