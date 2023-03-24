package view;

import java.util.Arrays;

public enum ResultCategory {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    ResultCategory(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    private boolean has(String display) {
        return this.result.equals(display);
    }

    public static ResultCategory of(String display) {
        return Arrays.stream(values())
                .filter(it -> it.has(display))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("게임 결과가 잘못되었습니다"));
    }
}
