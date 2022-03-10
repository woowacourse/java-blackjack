package blackjack.domain;

import java.util.List;

public enum WinDrawLose {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String name;

    WinDrawLose(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void judgeResult(Dealer dealer, Players players) {

    }
}
