package blackjack.domain.result;

import blackjack.domain.participant.Participant;

import java.util.Arrays;

public enum UserResult {

    LOSE("패", (user, dealer) -> user.isBust() || (dealer.isHit() && dealer.isMoreScore(user)) ||
            (dealer.isBlackJack() && !user.isBlackJack())),
    WIN("승", (user, dealer) -> dealer.isBust() || user.isMoreScore(dealer) ||
            (user.isBlackJack() && !dealer.isBlackJack())),
    DRAW("무", Participant::isSameScore);

    private final String name;
    private final ScoreComparator comparator;

    UserResult(String name, ScoreComparator comparator) {
        this.name = name;
        this.comparator = comparator;
    }

    public static UserResult checkUserResult(Participant user, Participant dealer) {
        return Arrays.stream(values())
                .filter(result -> result.compare(user, dealer))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static UserResult swap(UserResult result) {
        if (result == WIN) {
            return LOSE;
        }
        if (result == LOSE) {
            return WIN;
        }
        return result;
    }

    public boolean isWin() {
        return this == UserResult.WIN;
    }

    private boolean compare(Participant user, Participant dealer) {
        return comparator.compare(user, dealer);
    }

    public String getName() {
        return name;
    }
}
