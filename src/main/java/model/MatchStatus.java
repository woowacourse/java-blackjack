package model;

public enum MatchStatus {
    BLACKJACK("블랙잭 승", 1.5f),
    WIN("승", 1.0f),
    DRAW("무", 0.0f),
    LOSE("패", -1.0f);

    private final String status;
    private final float multiplier;

    MatchStatus(String status, float multiplier) {
        this.status = status;
        this.multiplier = multiplier;
    }

    public String getStatus() {
        return status;
    }

    public float getMultiplier() {
        return multiplier;
    }

}


