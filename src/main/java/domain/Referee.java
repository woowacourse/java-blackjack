package domain;

import static domain.Result.DRAW;
import static domain.Result.LOSE;
import static domain.Result.WIN;
import static domain.Result.WIN_BY_BLACKJACK;
import static domain.Status.BLACKJACK;
import static domain.Status.BUST;

import domain.user.User;

public class Referee {
    public static Result getResult(User user, User other) {
        return compare(user, other);
    }

    private static Result compare(User user, User other) {
        if (isWon(user, other)) {
            return confirmWinReason(user);
        }

        if (isDraw(user, other)) {
            return DRAW;
        }

        return LOSE;
    }

    private static boolean isWon(User user, User other) {
        if (user.status() == BUST) {
            return false;
        }

        if (user.status() == BLACKJACK && other.status() != BLACKJACK) {
            return true;
        }

        return user.score().isGreaterThan(other.score()) || other.status() == BUST;
    }

    private static boolean isDraw(User user, User other) {
        if (user.status() == BUST && other.status() == BUST) {
            return true;
        }

        if (user.status() == BLACKJACK && other.status() == BLACKJACK) {
            return true;
        }

        return (user.score().equals(other.score())) && other.status() != BLACKJACK;
    }

    private static Result confirmWinReason(User user) {
        if (user.status() == BLACKJACK) {
            return WIN_BY_BLACKJACK;
        }
        return WIN;
    }
}
