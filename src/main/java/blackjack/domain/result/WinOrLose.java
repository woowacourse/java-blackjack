package blackjack.domain.result;

import java.util.Arrays;

public enum WinOrLose {
    WIN("승", true),
    LOSE("패", false);

    private String name;
    private boolean isWinner;

    WinOrLose(String name, boolean isWinner) {
        this.name = name;
        this.isWinner = isWinner;
    }

    public boolean getIsWinner() {
        return isWinner;
    }


    public static WinOrLose of(boolean isWinner) {
        return Arrays.stream(WinOrLose.values())
                .filter(winOrLose -> winOrLose.getIsWinner()== isWinner)
                .findFirst()
                .get();
    }

    public String getName() {
        return name;
    }
}
