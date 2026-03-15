package domain.model;

public enum PlayerStatus {
    BLACK_JACK("블랙잭"),
    WIN("승"),
    LOSS("패"),
    DRAW("무"),
    NONE("미정");

    private String name;

    PlayerStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
