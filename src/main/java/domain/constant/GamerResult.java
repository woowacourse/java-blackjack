package domain.constant;

public enum GamerResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");
    private final String result;

    GamerResult(String result) {
        this.result = result;
    }

    public GamerResult getOpponentGameResult() {
        if (this.equals(GamerResult.WIN)) {
            return GamerResult.LOSE;
        }
        if (this.equals(GamerResult.LOSE)) {
            return GamerResult.WIN;
        }
        return GamerResult.DRAW;
    }

    public String getResult() {
        return result;
    }
}
