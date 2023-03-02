package blackjack.domain;

public class Player extends Participant {

    private final Name name;
    private Result result;

    public Player(final String input) {
        this.name = new Name(input);
        this.result = Result.EMPTY;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return this.result;
    }
}
