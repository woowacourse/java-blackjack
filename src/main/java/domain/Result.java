package domain;

public enum Result {
    WIN("승 "),
    LOSE("패 "),
    TIE("무 ");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
