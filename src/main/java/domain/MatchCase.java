package domain;

import java.util.function.Consumer;

public enum MatchCase {
    WIN("승", BlackjackResult::increaseDealerLoseCount),
    LOSE("패", BlackjackResult::increaseDealerWinCount),
    DRAW("무", BlackjackResult::increaseDrawCount);

    private final String korDisplayName;
    private final Consumer<BlackjackResult> counter;

    MatchCase(String korDisplayName,  Consumer<BlackjackResult> counter) {
        this.korDisplayName = korDisplayName;
        this.counter = counter;
    }

    public String getKorDisplayName() {
        return korDisplayName;
    }

    public void increaseMatchCountOf(BlackjackResult blackjackResult) {
        counter.accept(blackjackResult);
    }
}
