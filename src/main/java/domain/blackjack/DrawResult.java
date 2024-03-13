package domain.blackjack;

class DrawResult {
    private final String failCause;
    private final boolean hasNextChance;

    static DrawResult success(boolean hasNextChance) {
        return new DrawResult(null, hasNextChance);
    }

    static DrawResult fail(Exception drawFailCause, boolean hasNextChance) {
        return new DrawResult(drawFailCause.getMessage(), hasNextChance);
    }

    static DrawResult fail(String failCause, boolean hasNextChance) {
        return new DrawResult(failCause, hasNextChance);
    }

    private DrawResult(String failCause, boolean hasNextChance) {
        this.failCause = failCause;
        this.hasNextChance = hasNextChance;
    }

    boolean hasNextChance() {
        return hasNextChance;
    }

    boolean isSuccess() {
        return failCause == null;
    }

    String getFailCause() {
        return failCause;
    }
}
