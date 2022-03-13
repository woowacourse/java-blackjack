package blackjack.domain.result;

public class UserResult {

    private String name;
    private Result result;

    private UserResult(String name, Result result) {
        this.name = name;
        this.result = result;
    }

    public static UserResult of(String name, int userScore, int dealerScore) {
        return new UserResult(name, Result.checkUserResult(userScore, dealerScore));
    }

    public String getName() {
        return name;
    }

    public Result getResult() {
        return result;
    }
}
