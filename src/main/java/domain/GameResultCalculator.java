package domain;

public class GameResultCalculator {
    public static TotalFinalResult checkGameResult(Players players, Dealer dealer){
        return TotalFinalResult.from(players.getPlayers().stream()
                .map(player -> checkFinalResult(player, dealer))
                .toList());
    }

    private static FinalResult checkFinalResult(Player player, Dealer dealer) {
        return FinalResult.from(player.getName(),
                getResultType(player.getHand().getScore(),
                        dealer.getHand().getScore()));
    }

    private static ResultType getResultType(Score playerScore, Score dealerScore) {
        if (playerScore.isBust() || (playerScore.getValue() < dealerScore.getValue() && !dealerScore.isBust())) {
            return ResultType.LOSE;
        }
        if (dealerScore.isBust() || playerScore.getValue() > dealerScore.getValue()) {
            return ResultType.WIN;
        }
        return ResultType.DRAW;
    }
}
