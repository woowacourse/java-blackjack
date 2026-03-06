package blackjack.service;

import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Player;
import blackjack.domain.ScoreCompareResult;
import java.util.HashMap;
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

    public GameResult judgeTotalGameResult(List<Player> players, Dealer dealer) {
        Map<ScoreCompareResult, Integer> dealerResult = new HashMap<>();
        Map<Player, ScoreCompareResult> playerResults = new HashMap<>();
        for (Player player : players) {
            if (compareScore(player, dealer).equals(ScoreCompareResult.PLAYER_WIN)) {
                dealerResult.put(ScoreCompareResult.DEALER_LOSS,
                        dealerResult.getOrDefault(ScoreCompareResult.DEALER_LOSS, 0) + 1);
                playerResults.put(player, ScoreCompareResult.PLAYER_WIN);
            }
            if (compareScore(player, dealer).equals(ScoreCompareResult.DEALER_WIN)) {
                dealerResult.put(ScoreCompareResult.DEALER_WIN,
                        dealerResult.getOrDefault(ScoreCompareResult.DEALER_WIN, 0) + 1);
                playerResults.put(player, ScoreCompareResult.PLAYER_LOSS);
            }
            if (compareScore(player, dealer).equals(ScoreCompareResult.PUSH)) {
                dealerResult.put(ScoreCompareResult.PUSH,
                        dealerResult.getOrDefault(ScoreCompareResult.PUSH, 0) + 1);
                playerResults.put(player, ScoreCompareResult.PUSH);
            }
        }
        return new GameResult(dealerResult, playerResults);
    }

    public ScoreCompareResult compareScore(Player player, Dealer dealer) {
        boolean isPlayerBust = player.isBust();
        boolean isDealerBust = dealer.isBust();

        if (isPlayerBust || isDealerBust) {
            return compareScoreWhenBust(isPlayerBust, isDealerBust);
        }

        return compareScoreWhenNotBust(player.calculateTotalScore(), dealer.calculateTotalScore());

    }

    private ScoreCompareResult compareScoreWhenBust(boolean isPlayerBust, boolean isDealerBust) {
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

    private ScoreCompareResult compareScoreWhenNotBust(int playerTotalScore, int dealerTotalScore) {
        if (playerTotalScore > dealerTotalScore) {
            return ScoreCompareResult.PLAYER_WIN;
        }
        if (playerTotalScore < dealerTotalScore) {
            return ScoreCompareResult.DEALER_WIN;
        }
        return ScoreCompareResult.PUSH;
    }
}
