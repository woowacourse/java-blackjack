public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String value;

    Result(String value) {
        this.value = value;
    }
}
