package blackjack.service;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

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

    // 초기 카드 분배
    public void distributeInitialCards(List<Player> players, Dealer dealer) {
        cardDistributor.distributeInitialCards(players, dealer);
    }

    // 플레이어에게 한 장 분배
    public void drawCardToPlayer(Player player) {
        cardDistributor.distributeCardToPlayer(player);
    }

    // 승패 판정 로직
    public GameResult judgeTotalGameResult(List<Player> players, Dealer dealer) {
        Map<ScoreCompareResult, Integer> dealerResult = new HashMap<>();
        Map<Player, ScoreCompareResult> playerResults = new HashMap<>();

        for (Player player : players) {
            ScoreCompareResult result = compareScore(player, dealer);
            playerResults.put(player, toPlayerResult(result));
            dealerResult.merge(toDealerKey(result), 1, Integer::sum);
        }

        return new GameResult(dealerResult, playerResults);
    }

    public ScoreCompareResult compareScore(Player player, Dealer dealer) {
        boolean isPlayerBust = player.isBust();
        boolean isDealerBust = dealer.isBust();

        if (isPlayerBust || isDealerBust) {
            return compareScoreWhenBust(isPlayerBust);
        }

        return compareScoreWhenNotBust(player.calculateTotalScore(), dealer.calculateTotalScore());
    }

    private ScoreCompareResult toPlayerResult(ScoreCompareResult result) {
        if (result == ScoreCompareResult.DEALER_WIN) {
            return ScoreCompareResult.PLAYER_LOSS;
        }
        return result;
    }

    private ScoreCompareResult toDealerKey(ScoreCompareResult result) {
        if (result == ScoreCompareResult.PLAYER_WIN) {
            return ScoreCompareResult.DEALER_LOSS;
        }
        return result;
    }

    private ScoreCompareResult compareScoreWhenBust(boolean isPlayerBust) {
        if (isPlayerBust) {
            return ScoreCompareResult.DEALER_WIN;
        }
        return ScoreCompareResult.PLAYER_WIN;
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
