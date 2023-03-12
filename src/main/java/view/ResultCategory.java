package view;

import domain.Result;

public enum ResultCategory {

    WIN("승"), LOSE("패"), DRAW("무");

    private final String display;

    ResultCategory(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public static ResultCategory of(Result result) {
        if (result == Result.WIN || result == Result.WIN_BY_BLACKJACK) {
            return ResultCategory.WIN;
        }
        if (result == Result.DRAW) {
            return ResultCategory.DRAW;
        }
        return ResultCategory.LOSE;
    }
}
