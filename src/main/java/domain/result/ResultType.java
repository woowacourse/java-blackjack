package domain.result;

public enum ResultType {

    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    private final String result;

    ResultType(String result) {
        this.result = result;
    }

    public ResultType reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getResult() {
        return result;
    }
}
