package blackJack.domain.result;

public enum WinOrLose {
    WIN("승"),
    DRAW("무"),
    LOSE("패")
    ;

    private static final int BLACK_JACK = 21;
    private final String result;

    WinOrLose(String result) {
        this.result = result;
    }

    public static boolean overBlackJackScore(int score) {
        return score > BLACK_JACK;
    }

    public String getResult() {
        return result;
    }
}
