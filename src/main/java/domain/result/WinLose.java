package domain.result;

import domain.gamer.Gamer;

public enum WinLose {
    WIN("승"),
    LOSE("패");

    private final String value;

    WinLose(String value) {
        this.value = value;
    }

    public static WinLose determineWinLose(Gamer me, Gamer counterPart) {
        if (me.calculateScore().isBiggerThan(counterPart.calculateScore())) {
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
