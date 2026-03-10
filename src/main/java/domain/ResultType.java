package domain;

public enum ResultType {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String type;

    ResultType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
