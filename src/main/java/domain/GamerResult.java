package domain;

public enum GamerResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private String result;

    private GamerResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
