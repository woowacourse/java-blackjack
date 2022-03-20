package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    BLACKJACK_WIN(1.5, isBlackJackWin()),

    WIN(1, isWin()),

    DRAW(0, isDraw()),

    LOSE(-1, isLose());

    private static BiPredicate<Score, Score> isBlackJackWin() {
        return (dealer, participant) -> compareBlackJack(participant, dealer);
    }

    private static BiPredicate<Score, Score> isWin() {
        return (dealer, participant) ->
                compareTotalScore(participant, dealer)
                        || (dealer.isBust() && !participant.isBust());
    }

    private static BiPredicate<Score, Score> isDraw() {
        return (dealer, participant) ->
                (!dealer.isBlackJack() && (dealer.getTotal() == participant.getTotal()) && !participant.isBust())
                        || (dealer.isBlackJack() && participant.isBlackJack());
    }

    private static BiPredicate<Score, Score> isLose() {
        return (dealer, participant) ->
                participant.isBust()
                        || compareTotalScore(dealer, participant)
                        || compareBlackJack(dealer, participant);
    }

    private static boolean compareBlackJack(Score s1, Score s2) {
        return s1.isBlackJack() && !s2.isBlackJack();
    }

    private static boolean compareTotalScore(Score s1, Score s2) {
        return (s1.getTotal() > s2.getTotal()) && !s1.isBust();
    }

    private final double yield;
    private final BiPredicate<Score, Score> condition;

    Result(final double yield, final BiPredicate<Score, Score> condition) {
        this.yield = yield;
        this.condition = condition;
    }

    public static Result decide(final Score dealerScore, final Score participantScore) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(dealerScore, participantScore))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 승패를 가릴 수 없는 경우입니다."));
    }

    public double getYield() {
        return yield;
    }
}
