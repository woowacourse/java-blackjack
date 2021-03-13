package blackjack.domain.user;

public class WinningResultDTO {
    private final String name;
    private final int money;
    private final MatchResult result;

    public WinningResultDTO(String name, int money, MatchResult matchResult) {
        this.name = name;
        this.money = money;
        this.result = matchResult;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public MatchResult getResult() {
        return result;
    }
}
