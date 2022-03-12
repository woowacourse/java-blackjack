package blackjack.domain.result;

public class ParticipantResult {

    private final String name;
    private Result result;

    public ParticipantResult(String name) {
        this.name = name;
        this.result = Result.LOSE;
    }

    public void makeWin() {
        result = Result.WIN;
    }

    public String getName() {
        return name;
    }

    public Result getResult() {
        return result;
    }
}
