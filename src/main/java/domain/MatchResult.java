package domain;

public enum MatchResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private static final int BLACKJACK_MAX_LIMIT = 21;

    private final String name;

    MatchResult(String name) {
        this.name = name;
    }

    public static MatchResult of(int gamblerScore, int dealerScore) {
        int normalizeGamblerScore = normalize(gamblerScore);
        int normalizeDealerScore = normalize(dealerScore);

        if (normalizeGamblerScore > normalizeDealerScore) {
            return WIN;
        }

        if (normalizeGamblerScore < normalizeDealerScore) {
            return LOSE;
        }
        return DRAW;
    }

    private static int normalize(int score) {
        if (score > BLACKJACK_MAX_LIMIT) {
            return 0;
        }
        return score;
    }

    public String getName() {
        return name;
    }
}
