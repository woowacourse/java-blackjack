package first.domain.result;

public enum WinLose {
    WIN,
    LOSE;

    public static WinLose reverse(WinLose winLose) {
        if (winLose == WIN) {
            return LOSE;
        }
        return WIN;
    }
}
