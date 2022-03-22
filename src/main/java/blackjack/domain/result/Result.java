package blackjack.domain.result;

import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {

    BLACKJACK_WIN(1.5, isBlackJackWin()),

    WIN(1, isWin()),

    DRAW(0, isDraw()),

    LOSE(-1, isLose());

    private static BiPredicate<Player, Player> isBlackJackWin() {
        return (dealer, participant) -> compareBlackJack(participant, dealer);
    }

    private static BiPredicate<Player, Player> isWin() {
        return (dealer, participant) ->
                compareTotalScore(participant, dealer)
                        || (dealer.isBust() && !participant.isBust());
    }

    private static BiPredicate<Player, Player> isDraw() {
        return (dealer, participant) ->
                (!dealer.isBlackJack() && (dealer.getTotal() == participant.getTotal()) && !participant.isBust())
                        || (dealer.isBlackJack() && participant.isBlackJack());
    }

    private static BiPredicate<Player, Player> isLose() {
        return (dealer, participant) ->
                participant.isBust()
                        || compareTotalScore(dealer, participant)
                        || compareBlackJack(dealer, participant);
    }

    private static boolean compareBlackJack(Player p1, Player p2) {
        return p1.isBlackJack() && !p2.isBlackJack();
    }

    private static boolean compareTotalScore(Player p1, Player p2) {
        return (p1.getTotal() > p2.getTotal()) && !p1.isBust();
    }

    private final double yield;
    private final BiPredicate<Player, Player> condition;

    Result(final double yield, final BiPredicate<Player, Player> condition) {
        this.yield = yield;
        this.condition = condition;
    }

    public static Result decide(final Player dealer, final Player participant) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(dealer, participant))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 승패를 가릴 수 없는 경우입니다."));
    }

    public double getYield() {
        return yield;
    }
}
