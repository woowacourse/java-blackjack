package domain.result;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {

    public Referee() {
    }

    public Map<Player, GameResult> judge(Dealer dealer, Players players) {
        Map<Player, GameResult> judgeMap = new LinkedHashMap<>();
        for (Player player : players.players()) {
            GameResult playerResult = judge(dealer, player);
            judgeMap.put(player, playerResult);
        }
        return Collections.unmodifiableMap(judgeMap);
    }

    public Map<GameResult, Integer> judgeDealer(Dealer dealer, Players players) {
        Map<GameResult, Integer> judgeDealerMap = new EnumMap<>(GameResult.class);

        for (GameResult gameResult : GameResult.values()) {
            judgeDealerMap.put(gameResult, 0);
        }

        for (Player player : players.players()) {
            GameResult dealerResult = judge(player, dealer);
            judgeDealerMap.put(dealerResult, judgeDealerMap.getOrDefault(dealerResult, 0) + 1);
        }
        return Collections.unmodifiableMap(judgeDealerMap);
    }

    public GameResult judge(Participant dealer, Participant player) {
        if (player.isBlackJack() && !dealer.isBlackJack()) {
            return GameResult.BLACKJACK;
        }
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        return compareScore(dealer.getScore(), player.getScore());
    }

    private GameResult compareScore(int dealerScore, int playerScore) {
        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }
        if (playerScore < dealerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
