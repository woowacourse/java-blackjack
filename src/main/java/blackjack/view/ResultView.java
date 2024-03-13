package blackjack.view;

import blackjack.model.result.ResultState;

import java.util.Arrays;

public enum ResultView {
    WIN("승"),
    LOSE("패"),
    TIE("무");

    private final String resultName;

    ResultView(String resultName) {
        this.resultName = resultName;
    }

    public static String convertResultName(ResultState resultState) {
        return Arrays.stream(ResultView.values())
                .filter(resultView -> resultView.name().equals(resultState.name()))
                .findFirst()
                .orElseThrow()
                .resultName;
    }
}
