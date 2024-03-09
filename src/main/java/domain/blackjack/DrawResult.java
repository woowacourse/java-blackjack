package domain.blackjack;

public class DrawResult {
    private final String failCause;
    private final boolean hasNextChance;

    public static DrawResult success(boolean hasNextChance) {
        return new DrawResult(null, hasNextChance);
    }

    public static DrawResult fail(Exception drawFailCause, boolean hasNextChance) {
        return new DrawResult(drawFailCause.getMessage(), hasNextChance);
    }

    public static DrawResult fail(String failCause, boolean hasNextChance) {
        return new DrawResult(failCause, hasNextChance);
    }

    private DrawResult(String failCause, boolean hasNextChance) {
        this.failCause = failCause;
        this.hasNextChance = hasNextChance;
    }

    public boolean hasNextChance() {
        return hasNextChance;
    }

    public boolean isSuccess() {
        return failCause == null;
    }

    String getFailCause() {
        return failCause;
    }
}
