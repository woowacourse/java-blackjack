package view.mapper;

import domain.WinState;
import java.util.Arrays;

public enum WinStateMapper {

    WIN(WinState.WIN, "승"),
    LOSE(WinState.LOSE, "패"),
    DRAW(WinState.DRAW, "무");

    private final WinState winState;
    private final String expression;

    WinStateMapper(WinState winState, String expression) {
        this.winState = winState;
        this.expression = expression;
    }

    public static String toExpression(WinState winState) {
        return Arrays.stream(values())
                .filter(winStateMapper -> winStateMapper.winState == winState)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 승리 상태입니다."))
                .expression;
    }
}
