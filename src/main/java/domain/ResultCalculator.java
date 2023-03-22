package domain;

import java.util.ArrayList;
import java.util.List;

public class ResultCalculator {

    private ResultCalculator() {
    }

    public static int sumDealerProfit(List<Player> players) {
        List<Integer> finalProfits = new ArrayList<>();
        players.forEach(
            player -> finalProfits.add(player.calculateFinalProfit())
        );
        int finalDealerProfit = -finalProfits.stream()
            .mapToInt(Integer::intValue).sum();
        return finalDealerProfit;
    }

    public static void calculateBettingResultAfterGame(Dealer dealer, List<Player> players) {
        List<Result> winningResult = ResultCalculator.getWinningResult(dealer, players);
        for (int i = 0; i < players.size(); i++) {
            players.get(i).calculateFinalBettingResult(winningResult.get(i));
        }
    }

    public static List<Result> getWinningResult(final Dealer dealer, final List<Player> players) {
        List<Result> winningResult = new ArrayList<>();
        for (int index = 0; index < players.size(); index++) {
            Player player = players.get(index);
            Result result = Result.checkWinningResult(dealer.sumOfParticipantCards(), player.sumOfParticipantCards());
            winningResult.add(result);
        }
        return winningResult;
    }



}
