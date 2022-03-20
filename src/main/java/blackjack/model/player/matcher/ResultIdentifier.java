package blackjack.model.player.matcher;

import blackjack.model.player.Dealer;
import blackjack.model.player.Gamer;

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
            return Result.draw(gamer.bettingMoney());
        }
        if (gamer.isBlackjack()) {
            return Result.blackjack(gamer.bettingMoney());
        }
        return Result.loss(gamer.bettingMoney());
    }

    private boolean hasBust(Dealer dealer, Gamer gamer) {
        return dealer.isBust() || gamer.isBust();
    }

    private Result bustCaseResult(Gamer gamer) {
        if (gamer.isBust()) {
            return Result.loss(gamer.bettingMoney());
        }
        return Result.win(gamer.bettingMoney());
    }

    private Result compareWithScore(Dealer dealer, Gamer gamer) {
        if (gamer.lessScoreThan(dealer)) {
            return Result.loss(gamer.bettingMoney());
        }
        if (gamer.moreScoreThan(dealer)) {
            return Result.win(gamer.bettingMoney());
        }
        return Result.draw(gamer.bettingMoney());
    }
}
