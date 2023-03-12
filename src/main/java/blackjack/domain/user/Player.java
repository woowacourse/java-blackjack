package blackjack.domain.user;

public class Player extends User {

    private final BetAmount betAmount;

    public Player(Name name, BetAmount betAmount) {
        super(name);
        this.betAmount = betAmount;
    }

    public String getPlayerName() {
        return name.getName();
    }

    public int getBetAmount() {
        return betAmount.getBetAmount();
    }
}
