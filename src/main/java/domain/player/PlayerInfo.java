package domain.player;

public class PlayerInfo {

    private final Name name;
    private final BetAmount betAmount;

    private PlayerInfo(final Name name, final BetAmount betAmount) {
        this.name = name;
        this.betAmount = betAmount;
    }

    public static PlayerInfo of(String name, int betAmount) {
        return new PlayerInfo(Name.of(name), BetAmount.from(betAmount));
    }

    public String getName() {
        return name.getName();
    }

    public int getBetAmount() {
        return betAmount.getBetAmount();
    }
}
