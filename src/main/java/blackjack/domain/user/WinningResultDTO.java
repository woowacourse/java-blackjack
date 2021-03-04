package blackjack.domain.user;

public class WinningResultDTO {
    private final String name;
    private final MatchResult result;

    public WinningResultDTO(String name, MatchResult matchResult) {
        this.result = matchResult;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MatchResult getResult() {
        return result;
    }
}
