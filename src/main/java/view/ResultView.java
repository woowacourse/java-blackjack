package view;

import domain.Result;

import java.util.Arrays;

public enum ResultView {
    WIN("승"),
    LOSE("패"),
    TIE("무");

    private static final String UNKNOWN_RESULT = "존재하지 않는 결과입니다.";
    private final String viewName;

    ResultView(final String viewName) {
        this.viewName = viewName;
    }

    public static String findName(final Result result) {
        return Arrays.stream(values())
                .filter(resultView -> resultView.name().equals(result.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNKNOWN_RESULT))
                .viewName;
    }
}
