package domain.vo;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Result result = (Result) o;
        return getVictory() == result.getVictory() && getDraw() == result.getDraw()
            && getDefeat() == result.getDefeat();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVictory(), getDraw(), getDefeat());
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
