package blackjack.domain;

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
        if (player.getPoint() < MAX_WINNING_POINT &&
                dealer.getPoint() < MAX_WINNING_POINT) {
            return compare(player, dealer);
        }

        if (player.getPoint() == MAX_WINNING_POINT && dealer.getPoint() == MAX_WINNING_POINT) {
            return checkAce(player, dealer);
        }

        if (player.getPoint() == MAX_WINNING_POINT) {
            return ResultType.WIN;
        }
        return ResultType.LOSE;
    }

    private static ResultType checkAce(Player player, Dealer dealer) {
        if (player.containsAce()) {
            if (dealer.containsAce()) {
                return ResultType.DRAW;
            }
            return ResultType.WIN;
        }

        if (dealer.containsAce()) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
    }


    private static ResultType compare(Player player, Dealer dealer) {
        int playerScore = player.getPoint();
        int dealerScore = dealer.getPoint();

        int compare = Integer.compare(playerScore, dealerScore);
        if (compare < 0) {
            return ResultType.LOSE;
        }
        if (compare > 0) {
            return ResultType.WIN;
        }
        return ResultType.DRAW;
    }
}
