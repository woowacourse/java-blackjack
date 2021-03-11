package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class ResultCalculator {

    private ResultCalculator() {
    }

    public static ResultType decideWinner(final Player player, final Dealer dealer) {
        if (player.isBusted()) {
            return ResultType.LOSE;
        }
        if (dealer.isBusted()) {
            return ResultType.WIN;
        }
        return findWinner(player, dealer);
    }

    private static ResultType findWinner(final Player player, final Dealer dealer) {
        if (bothUnderMaxScore(player, dealer)) {
            return compare(player, dealer);
        }
        if (bothMaxScore(player, dealer)) {
            return checkBlackjack(player, dealer);
        }
        return confirmPlayerMaxScore(player);
    }

    private static ResultType confirmPlayerMaxScore(final Player player) {
        if (player.isMaxScore()) {
            return ResultType.WIN;
        }
        return ResultType.LOSE;
    }

    private static boolean bothMaxScore(final Player player, final Dealer dealer) {
        return player.isMaxScore() && dealer.isMaxScore();
    }

    private static boolean bothUnderMaxScore(final Player player, final Dealer dealer) {
        return player.isUnderMaxScore() && dealer.isUnderMaxScore();
    }

    private static ResultType checkBlackjack(final Player player, final Dealer dealer) {
        if (player.hasBlackjack() && dealer.hasBlackjack()) {
            return ResultType.DRAW;
        }
        if (player.hasBlackjack()) {
            return ResultType.WIN;
        }
        if (dealer.hasBlackjack()) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
    }


    private static ResultType compare(final Player player, final Dealer dealer) {
        int compare = player.calculateScore().compareTo(dealer.calculateScore());
        if (compare < 0) {
            return ResultType.LOSE;
        }
        if (compare > 0) {
            return ResultType.WIN;
        }
        return ResultType.DRAW;
    }
}
