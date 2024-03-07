package blackjack.view;

import blackjack.domain.GameResult;
import java.util.Arrays;

public enum ResultView {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String resultView;

    ResultView(final String resultView) {
        this.resultView = resultView;
    }

    public static String toResultView(GameResult gameResult) {
        return Arrays.stream(ResultView.values())
                .filter(resultView -> resultView.name().equals(gameResult.name()))
                .findFirst()
                .orElseThrow()
                .resultView;
    }
}
