package blackjack.domain.game;

public enum PlayerState {

    BLACKJACK(1.5, "승"),
    WIN(1, "승"),
    LOSE(-1, "패"),
    TIE(0, "무");

    private final double multiple;
    private final String description;

    PlayerState(double multiple, String description) {
        this.multiple = multiple;
        this.description = description;
    }

    public double getMultiple() {
        return multiple;
    }

    public String getDescription() {
        return description;
    }
}
