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

    public void letDealerHitUntilThreshold(Deck deck, int threshold) {
        while(dealer.isHit(threshold)) {
            dealer.draw(deck.serve());
        }
    }

    public Map<Player, GameResult> makeGameResultForAllPlayer() {
        int dealerPoint = dealer.sumCardPool();
        Map<Player, GameResult> record = new HashMap<>();
        recordWhoWin(dealerPoint, record);
        recordWhoLose(dealerPoint, record);
        recordWhoDraw(dealerPoint, record);

        return record;
    }

    private void recordWhoLose(int dealerPoint, Map<Player, GameResult> record) {
        players.stream()
                .filter(player -> player.sumCardPool() < dealerPoint)
                .forEach(player -> record.put(player, GameResult.LOSE));
    }

    private void recordWhoWin(int dealerPoint, Map<Player, GameResult> record) {
        players.stream()
                .filter(player -> player.sumCardPool() > dealerPoint)
                .forEach(player -> record.put(player, GameResult.WIN));
    }

    private void recordWhoDraw(int dealerPoint, Map<Player, GameResult> record) {
        players.stream()
                .filter(player -> player.sumCardPool() == dealerPoint)
                .forEach(player -> record.put(player, GameResult.DRAW));
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
}
