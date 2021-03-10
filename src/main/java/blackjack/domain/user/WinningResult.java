package blackjack.domain.user;

public class WinningResult {

    private final String name;
    private final MatchResult result;

    public WinningResult(User user, MatchResult matchResult) {
        this.name = user.getName();
        this.result = matchResult;
    }

    public String getName() {
        return name;
    }

    public MatchResult getResult() {
        return result;
    }
}
