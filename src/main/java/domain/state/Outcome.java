package domain.state;

public enum Outcome {
    WIN("승"), LOSE("패"), DRAW("무");

    private final String result;

    Outcome(final String result) {
        this.result = result;
    }

    public String result(){
        return result;
    }
}
