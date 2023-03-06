package domain.result;

import java.util.Arrays;

public enum GameResult {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private static final String NOT_FOUND_GAME_RESULT = "[ERROR] 해당하는 게임 결과가 존재하지 않습니다.";

    private final String status;

    GameResult(String status) {
        this.status = status;
    }

    public static String findGameResultName(GameResult result) {
        return Arrays.stream(values())
                .filter(gameResult -> gameResult.equals(result))
                .map(filteredResult -> filteredResult.status)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_GAME_RESULT));
    }
}
