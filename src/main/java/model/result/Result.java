package model.result;

import exception.IllegalResultException;

import java.util.Arrays;
import model.user.Dealer;
import model.user.Player;

public enum Result {
    BLACKJACK_DRAW(0) {
        boolean isResult(Player player, Dealer dealer) {
            return player.isBlackJack() && dealer.isBlackJack();
        }
    },
    BLACKJACK_WIN(1.5) {
        boolean isResult(Player player, Dealer dealer) {
            return player.isBlackJack();
        }
    },
    BLACKJACK_LOSE(-1) {
        boolean isResult(Player player, Dealer dealer) {
            return dealer.isBlackJack();
        }
    },
    BUST_LOSE(-1) {
        boolean isResult(Player player, Dealer dealer) {
            return player.isBust();
        }
    },
    BUST_WIN(1) {
        boolean isResult(Player player, Dealer dealer) {
            return dealer.isBust();
        }
    },
    WIN(1) {
        boolean isResult(Player player, Dealer dealer) {
            return Result.compareUser(player, dealer) == ratio;
        }
    },
    LOSE(-1){
        boolean isResult(Player player, Dealer dealer) {
            return Result.compareUser(player, dealer) == ratio;
        }
    },
    DRAW(0){
        boolean isResult(Player player, Dealer dealer) {
            return Result.compareUser(player, dealer) == ratio;
        }
    };

    double ratio;

    Result(double ratio) {
        this.ratio = ratio;
    }

    abstract boolean isResult(Player player, Dealer dealer);

    public static Result getResult(Dealer dealer, Player player) {
        return Arrays.stream(Result.values())
            .filter(result -> result.isResult(player, dealer))
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