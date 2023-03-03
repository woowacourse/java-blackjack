package blackjack.domain.game;

public enum WinTieLose {
    WIN("승"),
    TIE("무"),
    LOSE("패");
    private final String value;

    WinTieLose(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public WinTieLose reverseValue() {
        if (this.equals(WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return WIN;
        }
        return TIE;
    }
}