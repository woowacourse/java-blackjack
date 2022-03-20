package blackjack.model.player;

public class ResultIdentifier {

    public Result identify(Dealer dealer, Gamer gamer) {
        if (hasBlackjack(dealer, gamer)) {
            return blackjackCaseResult(dealer, gamer);
        }
        if (hasBust(dealer, gamer)) {
            return bustCaseResult(gamer);
        }
        return compareWithScore(dealer, gamer);
    }

    private boolean hasBlackjack(Dealer dealer, Gamer gamer) {
        return dealer.isBlackjack() || gamer.isBlackjack();
    }

    private Result blackjackCaseResult(Dealer dealer, Gamer gamer) {
        if (dealer.isBlackjack() && gamer.isBlackjack()) {
            return Result.DRAW;
        }
        if (gamer.isBlackjack()) {
            return Result.BLACKJACK;
        }
        return Result.LOSS;
    }

    private boolean hasBust(Dealer dealer, Gamer gamer) {
        return dealer.isBust() || gamer.isBust();
    }

    private Result bustCaseResult(Gamer gamer) {
        if (gamer.isBust()) {
            return Result.LOSS;
        }
        return Result.WIN;
    }

    private Result compareWithScore(Dealer dealer, Gamer gamer) {
        if (gamer.lessScoreThan(dealer)) {
            return Result.LOSS;
        }
        if (gamer.moreScoreThan(dealer)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }
}
