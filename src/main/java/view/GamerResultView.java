package view;

import domain.constant.GamerResult;
import java.util.Arrays;

public enum GamerResultView {
    WIN_VIEW(GamerResult.WIN, "승"),
    LOSE_VIEW(GamerResult.LOSE, "패"),
    DRAW_VIEW(GamerResult.DRAW, "무");

    private final GamerResult gamerResult;
    private final String symbol;

    GamerResultView(GamerResult gamerResult, String symbol) {
        this.gamerResult = gamerResult;
        this.symbol = symbol;
    }

    public static GamerResultView getViewByType(GamerResult gamerResult) {
        return Arrays.stream(values())
                .filter(gamerResultView -> gamerResultView.gamerResult == gamerResult)
                .findFirst()
                .orElse(null);
    }

    public String getSymbol() {
        return symbol;
    }
}
