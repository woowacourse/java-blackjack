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
        if (playerHand.isBust() || (dealerHand.isBlackjack() && !playerHand.isBlackjack() || (
                playerHand.getScore().value() < dealerHand.getScore().value()
                        && !dealerHand.isBust()))) {
            return ResultType.LOSE;
        }
        if (dealerHand.isBust() || (playerHand.isBlackjack() && !dealerHand.isBlackjack())
                || playerHand.getScore().value() > dealerHand.getScore().value()) {
            return checkBlackjackWin(playerHand);
        }
        return ResultType.DRAW;
    }

    private static ResultType checkBlackjackWin(Hand playerHand) {
        if (playerHand.isBlackjack()) {
            return ResultType.BLACKJACK_WIN;
        }
        return ResultType.WIN;
    }
}
