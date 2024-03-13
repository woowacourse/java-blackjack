package blackjack.domain.game;

public enum PlayerState {

    BLACKJACK(1.5, "승"),
    WIN(1, "승"),
    LOSE(-1, "패"),
    TIE(0, "무");

    private final String description;
    private final double multiple;

    PlayerState(double multiple, String description) {
        this.description = description;
        this.multiple = multiple;
    }

    public String getDescription() {
        return description;
    }

    public double getMultiple() {
        return multiple;
    }
}
