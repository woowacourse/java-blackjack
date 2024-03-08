package blackjack.view.description;

import blackjack.domain.GameResult;
import java.util.Arrays;

public enum GameResultDescription {
    WIN(GameResult.WIN, "승"),
    LOSE(GameResult.LOSE, "패"),
    DRAW(GameResult.DRAW, "무");

    private final GameResult gameResult;
    private final String description;

    GameResultDescription(GameResult gameResult, String description) {
        this.gameResult = gameResult;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescription(GameResult result) {
        return Arrays.stream(values())
                .filter(resultDescription -> resultDescription.gameResult == result)
                .findFirst()
                .map(GameResultDescription::getDescription)
                .orElseThrow();
    }
}
