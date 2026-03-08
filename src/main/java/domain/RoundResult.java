package domain;

public enum RoundResult {

    WIN("승"),LOSE("패"),DRAW("무");

    private final String result;

    RoundResult(String result) {
        this.result = result;
    }

    public String result() {
        return result;
    }
}
