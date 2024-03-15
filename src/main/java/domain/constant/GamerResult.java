package domain.constant;

public enum GamerResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    GamerResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
