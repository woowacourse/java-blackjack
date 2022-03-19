package blackjack.domain.game;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MatchResult {
    BLACKJACK(1.5, MatchResult::compareBlackjack),
    WIN(1, MatchResult::compareCards),
    PUSH(0, MatchResult::comparePush),
    LOSE(-1, (cards1, cards2) -> compareCards(cards2, cards1));

    private static final String ERROR_NOT_FIND = "유효하지 않은 카드 목록입니다.";
    private final double profit;
    private final BiPredicate<Cards, Cards> condition;

    MatchResult(double profit, BiPredicate<Cards, Cards> condition) {
        this.profit = profit;
        this.condition = condition;
    }

    /**
     * 파라미터로 주어진 카드 목록 두 개를 비교하고, 첫 번째 카드 목록이 승리할 경우 true 반환.
     *
     * @param cards1 - 참가자의 게임 정보, cards2 - cards1와 비교할 카드 목록
     * @return cards1이 승리 조건에 부합하면 true
     */
    private static boolean compareCards(Cards cards1, Cards cards2) {
        Status status1 = cards1.getStatus();
        Status status2 = cards2.getStatus();

        return (status1 == Status.BLACKJACK && status2 != Status.BLACKJACK)
                || (!status1.isBust() && status2.isBust())
                || (status1.isNone() && status2.isNone() && cards1.sum() > cards2.sum());
    }

    private static boolean compareBlackjack(Cards cards1, Cards cards2) {
        return cards1.getStatus().isBlackjack()
                && !cards2.getStatus().isBlackjack();
    }

    private static boolean comparePush(Cards cards1, Cards cards2) {
        Status status1 = cards1.getStatus();
        Status status2 = cards2.getStatus();

        return (!status1.isNone() && status1 == status2)
                || (status1.isNone() && cards1.sum() == cards2.sum());
    }

    public static long calculateProfit(Player player, Dealer dealer) {
        return (long) (player.getBettingAmount() * findProfit(player.getCards(), dealer.getCards()).profit);
    }

    private static MatchResult findProfit(Cards cards1, Cards cards2) {
        return Arrays.stream(MatchResult.values())
                .filter(matchResult -> matchResult.condition.test(cards1, cards2))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_FIND));
    }
}
