package model.result;

public enum ResultStatus {
    WIN("승"),
    PUSH("무"),
    LOOSE("패");

    private final String displayName;

    ResultStatus(String displayName) {
        this.displayName = displayName;
    }

    public ResultStatus oppositeStatus() {
        if (this == WIN) {
            return LOOSE;
        }
        if (this == LOOSE) {
            return WIN;
        }
        return PUSH;
    }

    public String getDisplayName() {
        return displayName;
    }
}
