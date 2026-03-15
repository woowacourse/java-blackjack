package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import utils.MatchCondition;

import java.util.function.BiPredicate;
import java.util.function.Consumer;

public enum MatchCase {
    LOSE("패", -1, BlackjackResult::increaseDealerWinCount, MatchCondition::isPlayerLose),
    DRAW("무", 0, BlackjackResult::increaseDrawCount, MatchCondition::isPlayerDraw),
    BLACKJACK_WIN("승", 1.5, BlackjackResult::increaseDealerLoseCount, MatchCondition::isPlayerBlackjackWin),
    WIN("승", 1, BlackjackResult::increaseDealerLoseCount, (dealer, player) -> true);

    private final String korDisplayName;
    private final double benefitRate;
    private final Consumer<BlackjackResult> counter;
    private final BiPredicate<Dealer, Player> judgeCriteria;


    MatchCase(String korDisplayName, double benefitRate, Consumer<BlackjackResult> counter, BiPredicate<Dealer, Player> judgeCriteria) {
        this.korDisplayName = korDisplayName;
        this.benefitRate = benefitRate;
        this.counter = counter;
        this.judgeCriteria = judgeCriteria;
    }

    public String getKorDisplayName() {
        return korDisplayName;
    }

    public double getBenefitRate() {
        return benefitRate;
    }

    public void increaseMatchCountOf(BlackjackResult blackjackResult) {
        counter.accept(blackjackResult);
    }

    public boolean isMatch(Dealer dealer, Player player) {
        return judgeCriteria.test(dealer, player);
    }
}
