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

    static DrawResult fail() {
        return new DrawResult("카드를 더이상 뽑을 수 없습니다.", false);
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
