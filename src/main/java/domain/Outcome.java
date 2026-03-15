package domain;

public enum Outcome {
    WIN("승"), LOSE("패");

    private final String result;

    Outcome(final String result) {
        this.result = result;
    }

    public String result(){
        return result;
    }
}
