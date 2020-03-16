package domain.result;

import domain.gamer.Gamer;

public enum WinLose {
    WIN("승"),
    LOSE("패");

    private final String value;

    WinLose(String value) {
        this.value = value;
    }

    public static Gamer determineWinner(Gamer gamer1, Gamer gamer2) {
        Score gamer1Score = gamer1.calculateScore();
        Score gamer2Score = gamer2.calculateScore();

        if (gamer1Score.isBiggerThan(gamer2Score)) {
            return gamer1;
        }
        return gamer2;
    }

    public static WinLose reverse(WinLose winLose) {
        if (winLose == WIN) {
            return LOSE;
        }
        return WIN;
    }

    public String getValue() {
        return value;
    }
}
