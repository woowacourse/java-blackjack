package domain.gamer;

public enum PlayerResult {
    BLACKJACKWIN(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    final double ratio;

    PlayerResult(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return this.ratio;
    }
}
