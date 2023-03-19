package domain;

public final class PlayerResult {
    private final Name name;
    private final BettingResult bettingResult;

    public PlayerResult(final Name name, final BettingResult bettingResult) {
        this.name = name;
        this.bettingResult = bettingResult;
    }

    public Name getName() {
        return name;
    }

    public BettingResult getBettingResult() {
        return bettingResult;
    }
}
