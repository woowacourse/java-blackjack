package domain.result;

import java.util.*;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

public class ResultCalculator {

    private static final int MIN_BUST_VALUE = 22;
    private static final String DELAER_NAME = "딜러";

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
        int playerCardValueSum = player.getCardValueSum();
        int dealerCardValueSum = dealer.getCardValueSum();
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
        if (participant.getName().equals(DELAER_NAME)) {
            gameResult = resultCount.findDealerGameResult();
        }
        if (!participant.getName().equals(DELAER_NAME)) {
            gameResult = resultCount.findPlayerGameResult();
        }
        return gameResult;
    }


//    public void executeGame(Players players, Dealer dealer) {
//        for (Player player : players.getPlayers()) {
//            fight(player, dealer);
//        }
//    }

//    public void fight(Player player, Dealer dealer) {
//        int playerTotalValue = checkBust(player.getMaxSum());
//        int dealerTotalValue = checkBust(dealer.getMaxSum());
//        List<Integer> playerResult = results.get(player.getName());
//        List<Integer> dealerResult = results.get(dealer.getName());
//        if (playerTotalValue > dealerTotalValue) {
//            playerWin(playerResult, dealerResult);
//        }
//        if (playerTotalValue < dealerTotalValue) {
//            dealerWin(playerResult, dealerResult);
//        }
//        if (playerTotalValue == dealerTotalValue) {
//            draw(playerResult, dealerResult);
//        }
//    }
//
//    private void playerWin(List<Integer> playerResult, List<Integer> dealerResult) {
//        playerResult.set(0, playerResult.get(0) + 1);
//        dealerResult.set(2, dealerResult.get(2) + 1);
//    }
//
//    private void dealerWin(List<Integer> playerResult, List<Integer> dealerResult) {
//        playerResult.set(2, playerResult.get(2) + 1);
//        dealerResult.set(0, dealerResult.get(0) + 1);
//    }
//
//    private void draw(List<Integer> playerResult, List<Integer> dealerResult) {
//        playerResult.set(1, playerResult.get(1) + 1);
//        dealerResult.set(1, dealerResult.get(1) + 1);
//    }
//
//    private int checkBust(int totalValue) {
//        if (totalValue > 21) {
//            totalValue = 0;
//        }
//        return totalValue;
//    }
//
//    public List<String> getFinalFightResults() {
//        List<String> finalFightResults = new ArrayList<>();
//        for (String name : results.keySet()) {
//            List<Integer> participantResult = results.get(name);
//            StringBuilder sb = getResultCount(name, participantResult);
//            finalFightResults.add(sb.toString());
//        }
//        return finalFightResults;
//    }
//
//    private StringBuilder getResultCount(String name, List<Integer> participantResult) {
//        StringBuilder sb = new StringBuilder(name + ": ");
//        if (participantResult.get(0) != 0) {
//            sb.append(participantResult.get(0)).append("승 ");
//        }
//        if (participantResult.get(1) != 0) {
//            sb.append(participantResult.get(1)).append("무 ");
//        }
//        if (participantResult.get(2) != 0) {
//            sb.append(participantResult.get(2)).append("패");
//        }
//        return sb;
//    }
//
//
//    public List<Integer> getResultsByName(String name) {
//        return results.get(name);
//    }
}
