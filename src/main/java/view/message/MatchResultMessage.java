package blackjack.view.message;

import blackjack.domain.MatchResult;

public enum MatchResultMessage {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    MatchResultMessage(String name) {
        this.name = name;
    }

    public static String of(MatchResult matchResult) {
        for (MatchResultMessage matchResultMessage : MatchResultMessage.values()) {
            if (matchResult.name().equals(matchResultMessage.name())) return matchResultMessage.getName();
        }

        throw new IllegalArgumentException("존재하지 않습니다.");
    }

    public String getName() {
        return name;
    }
}