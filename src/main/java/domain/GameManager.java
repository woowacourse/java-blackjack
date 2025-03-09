package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static domain.GameResult.*;

public class GameManager {
    public static final int LIMIT = 21;
    public static final int START_RECEIVE_CARD = 2;

    private final Dealer dealer;
    private final List<Player> players;

    public GameManager(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static GameManager create(final Dealer dealer, final List<Player> players) {
        dealer.receiveCard(START_RECEIVE_CARD);
        for (Player player : players) {
            player.receiveCard(START_RECEIVE_CARD);
        }
        return new GameManager(dealer, players);
    }

    public Map<GameResult, Integer> calculateDealerGameResult() {
        final List<GameResult> playerGameResult = calculatePlayerGameResult().values().stream().toList();
        return getAllGameResults().stream()
                .filter(playerGameResult::contains)
                .collect(Collectors.toMap(
                        GameResult::swapGameResult,
                        result -> Collections.frequency(playerGameResult, result),
                        (newResult, oldResult) -> oldResult
                ));
    }

    public Map<String, GameResult> calculatePlayerGameResult() {
        Map<String, GameResult> resultMap = new HashMap<>();
        for (Player player : players) {
            resultMap.put(player.getName(), calculateResult(dealer,player));
        }
        return resultMap;
    }

    public void dealerHitUntilStand(){
        dealer.hitCardUntilStand();
    }

    public int getDealerHitCount(){
        return dealer.getReceivedCardCount();
    }
}
