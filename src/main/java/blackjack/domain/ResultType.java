package blackjack.domain;

public enum ResultType {
    WIN("승"),
    LOSE("패"),
    PUSH("무");

    private final String type;

    ResultType(final String type) {
        this.type = type;
    }

    public static ResultType findBy(int totalPoint, int compareTotalPoint) {
        if(totalPoint > compareTotalPoint) {
            return WIN;
        }
        if(totalPoint < compareTotalPoint) {
            return LOSE;
        }
        return PUSH;
    }

    public String getType() {
        return type;
    }
}
