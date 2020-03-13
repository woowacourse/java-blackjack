package blackjack.domain.result;

import blackjack.domain.user.Player;
import blackjack.domain.user.User;

public enum ResultType {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String alias;

    ResultType(String alias) {
        this.alias = alias;
    }

    public static ResultType from(User targetUser, User compareUser) {
        if (targetUser instanceof Player && targetUser.isBust()) {
            return LOSE;
        }

        if (compareUser.isBust()) {
            return WIN;
        }

        if (targetUser.isBust()) {
            return LOSE;
        }

        if (targetUser.getScore() > compareUser.getScore()) {
            return WIN;
        }
        if (targetUser.getScore() < compareUser.getScore()) {
            return LOSE;
        }
        return DRAW;
    }

    public String getAlias() {
        return alias;
    }
}
