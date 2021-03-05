package blackjack.domain.utils;

import blackjack.domain.ResultType;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class ResultCalculator {

    private static final int MAX_WINNING_POINT = 21;

    private ResultCalculator() {
    }

    public static ResultType decideWinner(Player player, Dealer dealer) {
        if (player.getPoint() > MAX_WINNING_POINT) {
            return ResultType.LOSE;
        }
        if (dealer.getPoint() > MAX_WINNING_POINT) {
            return ResultType.WIN;
        }
        return findWinner(player, dealer);
    }

    private static ResultType findWinner(Player player, Dealer dealer) {
        if (bothUnderWinningPoint(player, dealer)) {
            return compare(player, dealer);
        }
        if (bothWinningPoint(player, dealer)) {
            return checkAce(player, dealer);
        }
        return confirmPlayerWinningPoint(player);
    }

    private static ResultType confirmPlayerWinningPoint(Player player) {
        if (player.getPoint() == MAX_WINNING_POINT) {
            return ResultType.WIN;
        }
        return ResultType.LOSE;
    }

    private static boolean bothWinningPoint(Player player, Dealer dealer) {
        return player.getPoint() == MAX_WINNING_POINT && dealer.getPoint() == MAX_WINNING_POINT;
    }

    private static boolean bothUnderWinningPoint(Player player, Dealer dealer) {
        return player.getPoint() < MAX_WINNING_POINT &&
                dealer.getPoint() < MAX_WINNING_POINT;
    }

    private static ResultType checkAce(Player player, Dealer dealer) {
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
        int compare = Integer.compare(player.getPoint(), dealer.getPoint());
        if (compare < 0) {
            return ResultType.LOSE;
        }
        if (compare > 0) {
            return ResultType.WIN;
        }
        return ResultType.DRAW;
    }
}
