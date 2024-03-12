package blackjack.view.object;

import blackjack.domain.gamer.GameResult;
import java.util.Arrays;

public enum GameResultOutput {

    WIN_OUTPUT(GameResult.WIN, "승"),
    LOSE_OUTPUT(GameResult.LOSE, "패");

    private final GameResult gameResult;
    private final String output;

    GameResultOutput(GameResult gameResult, String output) {
        this.gameResult = gameResult;
        this.output = output;
    }

    public static String convertGameResultToOutput(GameResult gameResult) {
        return Arrays.stream(values())
                .filter(gameResultOutput -> gameResultOutput.gameResult == gameResult)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 게임 결과가 존재하지 않아 문자열을 반환할 수 없습니다."))
                .output;
    }
}
