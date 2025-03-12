package domain;

public enum GameStatus {

    WIN("승"),
    TIE("무"),
    LOSE("패");

    private final String name;

    GameStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
