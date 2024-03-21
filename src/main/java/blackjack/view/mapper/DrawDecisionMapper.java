package blackjack.view.mapper;

import blackjack.domain.player.DrawDecision;
import java.util.Arrays;

public enum DrawDecisionMapper {

    HIT(DrawDecision.HIT, "y"),
    STAY(DrawDecision.STAY, "n"),
    ;

    private final DrawDecision drawDecision;
    private final String symbol;

    DrawDecisionMapper(DrawDecision drawDecision, String symbol) {
        this.drawDecision = drawDecision;
        this.symbol = symbol;
    }

    public static DrawDecision toDrawDecision(String symbol) {
        return Arrays.stream(values())
                .filter(it -> it.symbol.equals(symbol))
                .findFirst()
                .map(it -> it.drawDecision)
                .orElseThrow(IllegalArgumentException::new);
    }
}
