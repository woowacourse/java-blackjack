package blackjack.domain.game;

import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {

    BLACKJACK(1.5, (player, dealer) -> (player.isBlackJack() && !dealer.isBlackJack())),
    WIN(1, (player, dealer) -> isWin(player, dealer)),
    LOSE(-1, (player, dealer) -> isLose(player, dealer)),
    DRAW(0, (player, dealer) -> true);

    private final double leverage;
    private final BiPredicate<Participant, Participant> biPredicate;
    
    MatchResult(double leverage, BiPredicate<Participant, Participant> biPredicate) {
        this.leverage = leverage;
        this.biPredicate = biPredicate;
    }

    public static MatchResult judge(Participant player, Participant dealer) {
        return Arrays.stream(MatchResult.values())
                .filter(matchResult -> matchResult.biPredicate.test(player, dealer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 매칭 결과가 없습니다."));
    }

    private static boolean isWin(Participant player, Participant dealer) {
        return (player.isBlackJack() && !dealer.isBlackJack())
                || (!player.isBust() && (dealer.isBust() || player.compareSum(dealer) > 0));
    }

    private static boolean isLose(Participant player, Participant dealer) {
        return (dealer.isBlackJack() && !player.isBlackJack())
                || (!dealer.isBust() && (player.isBust() || player.compareSum(dealer) < 0));
    }

    public double calculateRevenue(Player player) {
        return player.calculateRevenue(this.leverage);
    }
}
