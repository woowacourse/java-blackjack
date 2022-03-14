package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GameScoreBoard {

    private final Map<CardScoreResult, Integer> dealerGameResult;
    private final Map<String, String> playerGameResultMap;

    public GameScoreBoard(Map<CardScoreResult, Integer> dealerGameResult,
        Map<String, String> playerGameResultMap) {
        this.dealerGameResult = dealerGameResult;
        this.playerGameResultMap = playerGameResultMap;
    }

    public static GameScoreBoard recordGameScore(Dealer dealer, List<Player> players) {
        Map<CardScoreResult, Integer> dealerResult = new EnumMap<>(CardScoreResult.class);
        Map<String, String> playerResult = new TreeMap<>();
        for (Player player : players) {
            CardScoreResult playerGameScore = CardScoreResult.findCardScoreResult(player, dealer);
            CardScoreResult dealerGameScore = playerGameScore.reverse();
            dealerResult.put(dealerGameScore, dealerResult.getOrDefault(dealerGameScore, 0) + 1);
            playerResult.put(player.getName(), playerGameScore.getName());
        }
        return new GameScoreBoard(dealerResult, playerResult);
    }

    public Map<String, String> getPlayerGameResultMap() {
        return Collections.unmodifiableMap(playerGameResultMap);
    }

    public Map<CardScoreResult, Integer> getDealerGameResult() {
        return Collections.unmodifiableMap(dealerGameResult);
    }
}
