package blackjack.service;

import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.ScoreCompareResult;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private final CardDistributor cardDistributor;

    public Game(CardDistributor cardDistributor) {
        this.cardDistributor = cardDistributor;
    }

    public void dealerDrawsCardsUntilDone(Dealer dealer) {
        while (!dealer.isDealerDone()) {
            cardDistributor.distributeCardToDealer(dealer);
        }
    }

    public static GameResult judgeTotalGameResult(List<Player> players, Dealer dealer) {
        Map<ScoreCompareResult, Integer> dealerResult = new HashMap<>();
        LinkedHashMap<Player, ScoreCompareResult> playerResults = new LinkedHashMap<>();

        for (Player player : players) {
            ScoreCompareResult result = compareScore(player, dealer);
            playerResults.put(player, toPlayerResult(result));
            dealerResult.merge(toDealerKey(result), 1, Integer::sum);
        }

        return new GameResult(dealerResult, playerResults);
    }

    private static ScoreCompareResult toPlayerResult(ScoreCompareResult result) {
        if (result == ScoreCompareResult.DEALER_WIN) {
            return ScoreCompareResult.PLAYER_LOSE;
        }
        return result;
    }

    private static ScoreCompareResult toDealerKey(ScoreCompareResult result) {
        if (result == ScoreCompareResult.PLAYER_WIN) {
            return ScoreCompareResult.DEALER_LOSE;
        }
        return result;
    }


    public static ScoreCompareResult compareScore(Player player, Dealer dealer) {
        boolean isPlayerBust = player.isBust();
        boolean isDealerBust = dealer.isBust();

        if (isPlayerBust || isDealerBust) {
            return compareScoreWhenBust(isPlayerBust, isDealerBust);
        }

        return compareScoreWhenNotBust(player.calculateTotalScore(), dealer.calculateTotalScore());

    }

    private static ScoreCompareResult compareScoreWhenBust(boolean isPlayerBust, boolean isDealerBust) {
        if (isPlayerBust && isDealerBust) {
            return ScoreCompareResult.PUSH;
        }
        if (isPlayerBust) {
            return ScoreCompareResult.DEALER_WIN;
        }
        if (isDealerBust) {
            return ScoreCompareResult.PLAYER_WIN;
        }
        throw new IllegalArgumentException("버스트인 사람이 1명은 포함되어야 합니다.");
    }

    private static ScoreCompareResult compareScoreWhenNotBust(int playerTotalScore, int dealerTotalScore) {
        if (playerTotalScore > dealerTotalScore) {
            return ScoreCompareResult.PLAYER_WIN;
        }
        if (playerTotalScore < dealerTotalScore) {
            return ScoreCompareResult.DEALER_WIN;
        }
        return ScoreCompareResult.PUSH;
    }
}
