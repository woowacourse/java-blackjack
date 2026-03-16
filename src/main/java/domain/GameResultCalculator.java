package domain;

public class GameResultCalculator {
    public static TotalFinalResult checkGameResult(Players players, Dealer dealer){
        return TotalFinalResult.from(players.getPlayers().stream()
                .map(player -> checkFinalResult(player, dealer))
                .toList());
    }

    private static FinalResult checkFinalResult(Player player, Dealer dealer) {
        return FinalResult.from(player,
                getResultType(player.getHand(), dealer.getHand()));
    }

    private static ResultType getResultType(Hand playerHand, Hand dealerHand) {
        if (playerHand.isBust()) {
            return ResultType.LOSE;
        }
        if (dealerHand.isBust()) {
            return ResultType.WIN;
        }
        if (playerHand.isBlackjack() || dealerHand.isBlackjack()) {
            return judgeBlackjack(playerHand, dealerHand);
        }

        return judgeScore(playerHand, dealerHand);
    }

    private static ResultType judgeBlackjack(Hand playerHand, Hand dealerHand) {
        if (playerHand.isBlackjack() && dealerHand.isBlackjack()) {
            return ResultType.DRAW;
        }
        if (playerHand.isBlackjack()) {
            return ResultType.BLACKJACK_WIN;
        }
        return ResultType.LOSE;
    }

    private static ResultType judgeScore(Hand playerHand, Hand dealerHand) {
        int playerScore = playerHand.getScore().value();
        int dealerScore = dealerHand.getScore().value();

        if (playerScore > dealerScore) {
            return ResultType.WIN;
        }
        if (playerScore < dealerScore) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
    }
}
