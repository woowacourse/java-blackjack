package blackjack.domain.result;

public class UserResult {

    private String name;
    private Result result;

    public UserResult(String name, Result result) {
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
