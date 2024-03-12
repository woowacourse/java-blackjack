package blackjack.view.display;

import blackjack.game.MatchResult;
import java.util.Arrays;

public enum PlayerResultDisplay {

    PLAYER_WIN(MatchResult.NORMAL_WIN, "승"),
    PLAYER_LOSE(MatchResult.LOSE, "패"),
    TIE(MatchResult.TIE, "무");

    private final MatchResult result;
    private final String notation;

    PlayerResultDisplay(MatchResult result, String notation) {
        this.result = result;
        this.notation = notation;
    }

    public static PlayerResultDisplay getDisplayByResult(MatchResult result) {
        return Arrays.stream(values())
                .filter(displayResult -> displayResult.result == result)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 결과입니다."));
    }

    public String getNotation() {
        return notation;
    }
}
