package blackjack.domain.game;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public enum WinTieLose {
    WIN("승"),
    TIE("무"),
    LOSE("패");

    private static final Map<WinTieLose, WinTieLose> reverse = new HashMap<>();

    private final String value;

    static {
        reverse.put(WIN, LOSE);
        reverse.put(TIE, TIE);
        reverse.put(LOSE, WIN);
    }

    WinTieLose(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public WinTieLose reverseValue() {
        return reverse.get(this);
    }
}
