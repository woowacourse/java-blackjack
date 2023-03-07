package blackjack.domain.game;

public enum WinTieLose {

    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String value;

    WinTieLose(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public WinTieLose reverse() {
        if (equals(WIN)) {
            return LOSE;
        }
        if (equals(TIE)) {
            return TIE;
        }
        return WIN;
    }
}
