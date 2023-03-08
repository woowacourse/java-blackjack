package blackjackgame;

public enum Result {
    WIN("승", 2),
    TIE("무", 1),
    LOSE("패", -2);

    private final String label;
    private final int rate;

    Result(String label, int rate) {
        this.label = label;
        this.rate = rate;
    }

    public String getLabel() {
        return label;
    }

    public int getRate() {
        return rate;
    }
}
