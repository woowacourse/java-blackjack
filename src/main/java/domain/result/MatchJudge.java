package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public class MatchJudge {
    private static final Map<MatchCase, BiPredicate<Dealer, Player>> JUDGE_CRITERIA =
            new LinkedHashMap<>(Map.of(
                    MatchCase.LOSE, MatchJudge::isPlayerLose,
                    MatchCase.DRAW, MatchJudge::isPlayerScoreEqualsDealer,
                    MatchCase.BLACKJACK_WIN, MatchJudge::isPlayerWinWithBlackjack
            ));

    private final Dealer dealer;
    private final Player player;

    public MatchJudge(Dealer dealer, Player player) {
        this.dealer = dealer;
        this.player = player;
    }

    private static boolean isPlayerWinWithBlackjack(Dealer dealer, Player player) {
        return !dealer.isBlackjack() && !isPlayerLose(dealer, player) && player.isBlackjack();
    }

    private static boolean isPlayerLose(Dealer dealer, Player player) {
        return player.isBust() || (!dealer.isBust() && player.getFinalScore() < dealer.getFinalScore());
    }

    private static boolean isPlayerScoreEqualsDealer(Dealer dealer, Player player) {
        return !(player.isBust() || dealer.isBust()) && (player.getFinalScore() == dealer.getFinalScore());
    }

    public MatchCase judge() {
        return JUDGE_CRITERIA.entrySet().stream()
                .filter(entry -> entry.getValue().test(this.dealer, this.player))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(MatchCase.WIN);
    }
}
