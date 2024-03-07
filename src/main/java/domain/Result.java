package domain;

public enum Result { //TODO 무승부

    WIN("승"),
    LOSE("패"),
    ;

    private final String value;

    Result(final String value) {
        this.value = value;
    }

    public Result reverse() {
        if (Result.WIN.equals(this)) {
            return LOSE;
        }
        return WIN;
    }
}
