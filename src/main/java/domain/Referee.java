package domain;

import domain.user.User;

import static domain.Status.BUST;

public class Referee {

    public static Result getResult(User user, User other) {
        return compare(user, other);
    }

    private static Result compare(User user, User other) {
        if (isDraw(user, other)) {
            return Result.DRAW;
        }

        if (isWon(user, other)) {
            return Result.WIN;
        }

        return Result.LOSE;
    }

    private static boolean isWon(User user, User other) {
        if (user.status() == BUST) {
            return false;
        }

        return user.score().isGreaterThan(other.score()) || other.status() == BUST;
    }

    private static boolean isDraw(User user, User other) {
        if (user.status() == BUST && other.status() == BUST) {
            return true;
        }

        return user.score().equals(other.score());
    }

}
