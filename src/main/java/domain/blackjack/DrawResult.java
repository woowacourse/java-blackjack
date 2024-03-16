package domain.blackjack;

public class DrawResult {
    private final String failCause;
    private final boolean hasNextChance;

    private DrawResult(String failCause, boolean hasNextChance) {
        this.failCause = failCause;
        this.hasNextChance = hasNextChance;
    }

    static DrawResult success(boolean hasNextChance) {
        return new DrawResult(null, hasNextChance);
    }

    static DrawResult fail(Exception drawFailCause, boolean hasNextChance) {
        return new DrawResult(drawFailCause.getMessage(), hasNextChance);
    }

    static DrawResult fail() {
        return new DrawResult("카드를 더이상 뽑을 수 없습니다.", false);
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
