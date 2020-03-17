package domain;

public enum WinningResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String korean;

    WinningResult(String korean) {
        this.korean = korean;
    }

    public String getKorean() {
        return korean;
    }
}
