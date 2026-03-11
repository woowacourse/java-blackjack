package domain.result;

import java.util.function.Consumer;

public enum MatchCase {
    BLACKJACK_WIN("승", 1.5, BlackjackResult::increaseDealerLoseCount),
    WIN("승", 1, BlackjackResult::increaseDealerLoseCount),
    LOSE("패", -1, BlackjackResult::increaseDealerWinCount),
    DRAW("무", 0, BlackjackResult::increaseDrawCount);

    private final String korDisplayName;
    private final double benefitRate;
    private final Consumer<BlackjackResult> counter;

    MatchCase(String korDisplayName, double benefitRate, Consumer<BlackjackResult> counter) {
        this.korDisplayName = korDisplayName;
        this.benefitRate = benefitRate;
        this.counter = counter;
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
}
