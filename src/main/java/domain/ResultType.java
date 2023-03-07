package domain;

public enum ResultType {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private String resultType;

    ResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getResultType() {
        return resultType;
    }

    public ResultType reverse() {
        if (this == ResultType.WIN) {
            return ResultType.LOSE;
        }
        if (this == ResultType.LOSE) {
            return ResultType.WIN;
        }
        return this;
    }

}
