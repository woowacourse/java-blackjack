package blackjack.domain.participant;

public class Player extends Participant {

    private final Name name;
    private Result result;

    public Player(final String input) {
        this.name = new Name(input);
        this.result = Result.EMPTY;
    }

    public Result getResult() {
        return this.result;
    }

    public String getName() {
        return this.name.getValue();
    }

    public void setResult(final Result result) {
        this.result = result;
    }
}
