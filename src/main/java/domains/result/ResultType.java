package domains.result;

public enum ResultType {
    WIN("승"), LOSE("패"), DRAW("무");

    private String resultType;

    ResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultType() {
        return resultType;
    }

    public ResultType oppose() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
