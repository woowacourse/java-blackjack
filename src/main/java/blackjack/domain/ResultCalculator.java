package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class ResultCalculator {

    private ResultCalculator() {
    }

    public static ResultType decideWinner(Player player, Dealer dealer) {
        if (player.isBusted()) {
            return ResultType.LOSE;
        }
        if (dealer.isBusted()) {
            return ResultType.WIN;
        }
        return findWinner(player, dealer);
    }

    private static ResultType findWinner(Player player, Dealer dealer) {
        if (bothUnderMaxScore(player, dealer)) {
            return compare(player, dealer);
        }
        if (bothMaxScore(player, dealer)) {
            return checkBlackjack(player, dealer);
        }
        return confirmPlayerMaxScore(player);
    }

    private static ResultType confirmPlayerMaxScore(Player player) {
        if (player.isMaxScore()) {
            return ResultType.WIN;
        }
        return ResultType.LOSE;
    }

    private static boolean bothMaxScore(Player player, Dealer dealer) {
        return player.isMaxScore() && dealer.isMaxScore();
    }

    private static boolean bothUnderMaxScore(Player player, Dealer dealer) {
        return player.isUnderMaxScore() && dealer.isUnderMaxScore();
    }

    private static ResultType checkBlackjack(Player player, Dealer dealer) {
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


    private static ResultType compare(Player player, Dealer dealer) {
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
