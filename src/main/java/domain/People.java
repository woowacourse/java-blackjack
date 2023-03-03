package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class People {

    private final List<Player> players;
    private final Dealer dealer;

    public People(List<String> playerNames, String dealerName) {
        this.players = mapToPlayers(playerNames);
        this.dealer = mapToDealer(dealerName);
    }

    private List<Player> mapToPlayers(List<String> playerNames) {
        return playerNames.stream()
                .map(playerName -> new Player(playerName, new CardPool(Collections.emptyList())))
                .collect(Collectors.toList());
    }

    private Dealer mapToDealer(String dealerName) {
        return new Dealer(dealerName, new CardPool(Collections.emptyList()));
    }

    public void letPlayersToHit(Deck deck) {
        for (Player player : players) {
            player.draw(deck.serve());
        }
        dealer.draw(deck.serve());
    }

    public void letPlayerToHit(String playerName, Deck deck) {
        Player player = playerByName(playerName);
        player.draw(deck.serve());
    }

    public void letDealerHitUntilThreshold(Deck deck) {
        while(dealer.isHit()) {
            dealer.draw(deck.serve());
        }
    }

    public Map<Player, GameResult> makeGameResultForAllPlayer() {
        Map<Player, GameResult> record = new HashMap<>();
        recordGameResult(record);

        return record;
    }

    private void recordGameResult(Map<Player, GameResult> record) {
        players.forEach(player -> record.put(player, GameResult.getResult(player, dealer)));
    }

    public Map<GameResult, Integer> getDealerRecord(Map<Player, GameResult> record) {
        return GameResult.makeDealerRecord(record);
    }

    public boolean isBurst(String playerName, int blackJackNumber) {
        return playerByName(playerName).sumCardPool() > blackJackNumber;
    }

    private Player playerByName(String playerName) {
        return players.stream()
                .filter(it -> it.hasSameNameWith(playerName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당하는 플레이어가 존재하지 않습니다"));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
