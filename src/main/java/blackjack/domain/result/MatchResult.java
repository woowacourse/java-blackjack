package blackjack.domain.result;

public class MatchResult {
    private String name;
    private Result result;

    public MatchResult(String name, Result result) {
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
