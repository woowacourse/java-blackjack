package blackjack.domain.cardgame;

public enum WinningStatus {
    WIN("승"),
    PUSH("무"),
    LOSE("패");
    
    // TODO: 이름 바꾸기
    private final String value;

    WinningStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
