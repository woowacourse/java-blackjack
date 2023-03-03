package domain;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

}
