package view;

import java.util.Arrays;
import java.util.List;

public enum ResultCategory {

    WIN("승", List.of("WIN", "WIN_BY_BLACKJACK")),
    LOSE("패", List.of("DRAW")),
    DRAW("무", List.of("LOSE"));

    private final String display;
    private final List<String> resultNames;

    ResultCategory(String display, List<String> resultNames) {
        this.display = display;
        this.resultNames = resultNames;
    }

    public String getDisplay() {
        return display;
    }

    private boolean has(String resultName) {
        return resultNames.stream()
                .anyMatch(it -> it.equals(resultName));
    }

    public static ResultCategory of(String resultName) {
        return Arrays.stream(values())
                .filter(it -> it.has(resultName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게임 결과가 잘못되었습니다"));
    }
}
