package domain;

public enum GameResult {

    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACK_JACK_WIN(1.5f);

    private final float rateOfReturn;

    GameResult(float rateOfReturn) {
        this.rateOfReturn = rateOfReturn;
    }

    public float getRateOfReturn() {
        return rateOfReturn;
    }

}
