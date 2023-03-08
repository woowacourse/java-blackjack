package domain.result;

import java.util.*;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import util.Constants;

public class ResultCalculator {

    private static final int MIN_BUST_VALUE = 22;

    private final Map<Participant, ResultCount> results = new LinkedHashMap<>();

    public ResultCalculator(Dealer dealer, Players players) {
        initResults(dealer, players);
    }

    private void initResults(Dealer dealer, Players players) {
        results.put(dealer, new ResultCount());
        for (Player player : players.getPlayers()) {
            results.put(player, new ResultCount());
        }
    }

    public void calculate(Player player, Dealer dealer) {
        GameResult gameResult = GameResult.WIN;
        ResultCount playerResultCount = results.get(player);
        ResultCount dealerResultCount = results.get(dealer);
        int playerCardValueSum = player.selectNotBustCardValueSum();
        int dealerCardValueSum = dealer.selectNotBustCardValueSum();
        gameResult = judgeGameResult(gameResult, playerCardValueSum, dealerCardValueSum);
        resultCalculate(gameResult, playerResultCount, dealerResultCount);
    }

    private GameResult judgeGameResult(GameResult gameResult, int playerCardValueSum, int dealerCardValueSum) {
        if (isBust(playerCardValueSum)) {
            gameResult = GameResult.LOSE;
        }
        if (!isBust(playerCardValueSum)) {
            gameResult = judgeGameResultWhenBust(gameResult, playerCardValueSum, dealerCardValueSum);
        }
        return gameResult;
    }

    private GameResult judgeGameResultWhenBust(GameResult gameResult, int playerCardValueSum, int dealerCardValueSum) {
        if (playerCardValueSum == dealerCardValueSum) {
            gameResult = GameResult.TIE;
        }
        if (playerCardValueSum > dealerCardValueSum) {
            gameResult = GameResult.WIN;
        }
        if (playerCardValueSum < dealerCardValueSum) {
            gameResult = GameResult.LOSE;
        }
        return gameResult;
    }

    private void resultCalculate(GameResult gameResult, ResultCount playerResultCount, ResultCount dealerResultCount) {
        if (gameResult.equals(GameResult.TIE)) {
            tie(playerResultCount, dealerResultCount);
        }
        if (gameResult.equals(GameResult.WIN)) {
            playerWin(playerResultCount, dealerResultCount);
        }
        if (gameResult.equals(GameResult.LOSE)) {
            playerLose(playerResultCount, dealerResultCount);
        }
    }

    private void playerWin(ResultCount playerResultCount, ResultCount dealerResultCount) {
        playerResultCount.addWinCount();
        dealerResultCount.addLoseCount();
    }

    private void tie(ResultCount playerResultCount, ResultCount dealerResultCount) {
        playerResultCount.addTieCount();
        dealerResultCount.addTieCount();
    }

    private void playerLose(ResultCount playerResultCount, ResultCount dealerResultCount) {
        playerResultCount.addLoseCount();
        dealerResultCount.addWinCount();
    }

    private boolean isBust(int cardValueSum) {
        return cardValueSum >= MIN_BUST_VALUE;
    }

    public Map<String, String> getFinalFightResults() {
        Map<String, String> finalFightResults = new LinkedHashMap<>();
        for (Participant participant : results.keySet()) {
            String playerOrDealerResult = getPlayerOrDealerResults(participant, results.get(participant));
            finalFightResults.put(participant.getName(), playerOrDealerResult);
        }
        return finalFightResults;
    }

    private String getPlayerOrDealerResults(Participant participant, ResultCount resultCount) {
        String gameResult = "";
        if (participant.getName().equals(Constants.DEALER_NAME)) {
            gameResult = resultCount.findDealerGameResult();
        }
        if (!participant.getName().equals(Constants.DEALER_NAME)) {
            gameResult = resultCount.findPlayerGameResult();
        }
        return gameResult;
    }
}
