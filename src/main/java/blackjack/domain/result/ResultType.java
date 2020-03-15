package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.Predicate;

import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import blackjack.domain.user.hand.Score;

public enum ResultType {
    WIN(value -> value > 0, "승"),
    DRAW(value -> value == 0, "무"),
    LOSE(value -> value < 0, "패");

    private Predicate<Integer> compareResult;
    private final String alias;

    ResultType(Predicate<Integer> compareResult, String alias) {
        this.compareResult = compareResult;
        this.alias = alias;
    }

    public static ResultType from(User targetUser, User compareUser) {
        return Arrays.stream(values())
            .filter(resultType -> resultType.compareResult.test(
                Integer.compare(targetUser.getScore(), compareUser.getScore())))
            .map(resultType -> findResultTypeWhenBothUserBust(targetUser, resultType))
            .findAny()
            .orElseThrow(() -> new NullPointerException("유효하지 않은 결과 타입입니다."));
    }

    private static ResultType findResultTypeWhenBothUserBust(User targetUser, ResultType resultType) {
        if (isBustDealerAndPlayer(targetUser, resultType)) {
            return findResultType(targetUser);
        }
        return resultType;
    }

    private static ResultType findResultType(User targetUser) {
        if (targetUser instanceof Player) {
            return ResultType.LOSE;
        }
        return ResultType.WIN;
    }

    private static boolean isBustDealerAndPlayer(User targetUser, ResultType resultType) {
        return resultType.alias.equals(DRAW.alias) && targetUser.getScore() == Score.BUST.getScore();
    }

    public String getAlias() {
        return alias;
    }
}
