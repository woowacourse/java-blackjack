package model;

public enum MatchStatus {
    WIN("승", 1.5f),
    DRAW("무", 1.0f),
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


