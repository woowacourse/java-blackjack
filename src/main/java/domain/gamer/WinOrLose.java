package domain.gamer;

public enum WinOrLose {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private static final int WIN_OR_LOSE_PIVOT = 0;
    private final String initial;

    WinOrLose(String initial) {
        this.initial = initial;
    }

    public static WinOrLose of(int intervalScore) {
        if (intervalScore > WIN_OR_LOSE_PIVOT) {
            return WIN;
        }

        if (intervalScore < WIN_OR_LOSE_PIVOT) {
            return LOSE;
        }

        return DRAW;
    }

    public String getInitial() {
        return initial;
    }
}
