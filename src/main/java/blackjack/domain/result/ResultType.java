package blackjack.domain.result;

public enum ResultType {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String label;

    ResultType(String label) {
        this.label = label;
    }

    public ResultType getOpposite() {
        if (this.equals(ResultType.WIN)) {
            return LOSE;
        }
        if (this.equals(ResultType.LOSE)) {
            return WIN;
        }
        return DRAW;
    }

    public String getLabel() {
        return label;
    }
}
