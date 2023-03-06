package domain.model;

public class Result {

    private int victory;
    private int draw;
    private int defeat;

    public Result(final int victory, final int draw, final int defeat) {
        this.victory = victory;
        this.draw = draw;
        this.defeat = defeat;
    }

    public void addVictory() {
        victory++;
    }

    public void addDraw() {
        draw++;
    }

    public void addDefeat() {
        defeat++;
    }

    public int getVictory() {
        return victory;
    }

    public int getDraw() {
        return draw;
    }

    public int getDefeat() {
        return defeat;
    }
}
