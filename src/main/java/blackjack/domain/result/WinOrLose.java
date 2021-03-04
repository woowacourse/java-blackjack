package blackjack.domain.result;

public enum WinOrLose {
    WIN("승"), LOSE("패"), TIE("무");

    private final String name;

    WinOrLose(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
