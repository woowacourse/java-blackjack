package model.result;

import exception.IllegalResultException;

import java.util.Arrays;
import model.user.Dealer;
import model.user.Player;

public enum Result {
    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0);

    double ratio;

    Result(double ratio) {
        this.ratio = ratio;
    }

    public static Result getResult(Dealer dealer, Player player) {
        return checkBlackJack(dealer, player);
    }

    private static Result checkBlackJack(Dealer dealer, Player player) {
        if (dealer.isBlackJack() && player.isBlackJack()) {
            return Result.DRAW;
        }
        if (dealer.isBlackJack()) {
            return Result.LOSE;
        }
        if (player.isBlackJack()) {
            return Result.BLACKJACK;
        }
        return checkBust(dealer, player);
    }

    private static Result checkBust(Dealer dealer, Player player) {
        if (player.isBust()) {
            return Result.LOSE;
        }
        if (dealer.isBust()) {
            return Result.WIN;
        }
        return compete(dealer, player);
    }

    private static Result compete(Dealer dealer, Player player) {
        return Arrays.stream(Result.values())
            .filter(result -> compareUser(player, dealer) == result.ratio)
            .findFirst()
            .orElseThrow(() -> new IllegalResultException("올바른 비교 값이 아닙니다."));
    }

    private static int compareUser(Player player, Dealer dealer) {
        return Integer.compare(player.getScore(), dealer.getScore());
    }

    public double calculateRevenue(Player player) {
        return player.multiplyBettingMoney(ratio);
    }
}