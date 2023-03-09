package domain.vo;

import java.util.Objects;

public class Result {

    private final int victory;
    private final int draw;
    private final int defeat;

    private Result(final int victory, final int draw, final int defeat) {
        this.victory = victory;
        this.draw = draw;
        this.defeat = defeat;
    }

    public static Result empty() {
        return new Result(0, 0, 0);
    }

    public static Result victory() {
        return new Result(1, 0, 0);
    }

    public static Result draw() {
        return new Result(0, 1, 0);
    }

    public static Result defeat() {
        return new Result(0, 0, 1);
    }

    public Result addVictory() {
        return new Result(victory + 1, draw, defeat);
    }

    public Result addDraw() {
        return new Result(victory, draw + 1, defeat);
    }

    public Result addDefeat() {
        return new Result(victory, draw, defeat + 1);
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
