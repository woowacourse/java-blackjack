package domain.result;

public enum WinLose {
    WIN("승"),
    LOSE("패");

    private final String value;

    WinLose(String value) {
        this.value = value;
    }

    public static WinLose determineWinLose(Score myScore, Score counterPartScore) {
        if (myScore.isBiggerThan(counterPartScore)) {
            return WinLose.WIN;
        }

        return WinLose.LOSE;
    }

    public WinLose reverse() {
        if (this == WIN) {
            return LOSE;
        }
        return WIN;
    }

    public String getValue() {
        return value;
    }
}
