package view.notations;

import domain.judge.WinState;

import java.util.Arrays;

public enum WinStateNotation {

    WIN(WinState.WIN, "승"),
    LOSE(WinState.LOSE, "패"),
    DRAW(WinState.DRAW, "무");

    private final WinState winState;
    private final String notation;

    WinStateNotation(WinState winState, String notation) {
        this.winState = winState;
        this.notation = notation;
    }

    public static String findNotationByWinState(WinState winState) {
        return Arrays.stream(values())
                .filter(winStateNotation -> winStateNotation.winState.equals(winState))
                .map(winStateNotation -> winStateNotation.notation)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 결과를 찾을 수 없습니다."));
    }
}
