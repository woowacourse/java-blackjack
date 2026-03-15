package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import utils.MatchCondition;

import java.util.function.BiPredicate;

public enum MatchCase {
    LOSE("패", -1, MatchCondition::isPlayerLose),
    DRAW("무", 0, MatchCondition::isPlayerDraw),
    BLACKJACK_WIN("승", 1.5, MatchCondition::isPlayerBlackjackWin),
    WIN("승", 1, (dealer, player) -> true);

    private final String korDisplayName;
    private final double benefitRate;
    private final BiPredicate<Dealer, Player> judgeCriteria;


    MatchCase(String korDisplayName, double benefitRate, BiPredicate<Dealer, Player> judgeCriteria) {
        this.korDisplayName = korDisplayName;
        this.benefitRate = benefitRate;
        this.judgeCriteria = judgeCriteria;
    }

    public String getKorDisplayName() {
        return korDisplayName;
    }

    public double getBenefitRate() {
        return benefitRate;
    }

    public boolean isMatch(Dealer dealer, Player player) {
        return judgeCriteria.test(dealer, player);
    }
}
