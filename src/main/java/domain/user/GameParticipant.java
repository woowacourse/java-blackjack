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

    public GameParticipant(List<String> playerNames, String dealerName) {
        this.players = makePlayersWithName(playerNames);
        this.dealer = makeDealerWithName(dealerName);
    }

    private List<Player> makePlayersWithName(List<String> playerNames) {
        return playerNames.stream()
                .map(playerName -> new Player(playerName, new CardPool(Collections.emptyList())))
                .collect(Collectors.toList());
    }

    private Dealer makeDealerWithName(String dealerName) {
        return new Dealer(dealerName, new CardPool(Collections.emptyList()));
    }

    public void letPlayersToHit(Deck deck) {
        for (Player player : players) {
            player.draw(deck.serve());
        }
        dealer.draw(deck.serve());
    }

    public void letPlayerToHit(String playerName, Deck deck) {
        Player player = getPlayerByName(playerName);
        player.draw(deck.serve());
    }

    public void letDealerHitUntilThreshold(Deck deck) {
        while (dealer.needsHit()) {
            dealer.draw(deck.serve());
        }
    }

    public Map<Player, GameResult> makeGameResultForAllPlayer() {
        Map<Player, GameResult> record = new HashMap<>();
        recordGameResult(record);

        return record;
    }

    private void recordGameResult(Map<Player, GameResult> record) {
        players.forEach(player -> record.put(player, GameResult.makePlayerRecord(player, dealer)));
    }

    public Map<GameResult, Integer> getDealerRecord(Map<Player, GameResult> record) {
        return GameResult.makeDealerRecord(record);
    }

    public boolean isBurst(String playerName) {
        return getPlayerByName(playerName).isBurst();
    }

    private Player getPlayerByName(String playerName) {
        return players.stream()
                .filter(it -> it.hasSameNameWith(playerName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 플레이어가 존재하지 않습니다"));
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
