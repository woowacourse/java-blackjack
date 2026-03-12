package model.judgement;

public enum GameStatus {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String name;

    GameStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
