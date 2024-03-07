package view.display;

import game.MatchResult;

import java.util.Arrays;

public enum PlayerResultDisplay {
    PLAYER_WIN(MatchResult.PLAYER_WIN, "승"),
    PLAYER_LOSE(MatchResult.DEALER_WIN, "패"),
    TIE(MatchResult.TIE, "무");

    private final MatchResult result;
    private final String display;

    PlayerResultDisplay(MatchResult result, String display) {
        this.result = result;
        this.display = display;
    }

    public static String getDisplayByResult(MatchResult result) {
        return Arrays.stream(values())
                .filter(displayResult -> displayResult.result == result)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 결과입니다."))
                .display;
    }
}
