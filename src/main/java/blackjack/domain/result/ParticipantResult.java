package blackjack.domain.result;

public class ParticipantResult {

    private final String name;
    private final Result result;

    public ParticipantResult(String name, Result result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public Result getResult() {
        return result;
    }
}
