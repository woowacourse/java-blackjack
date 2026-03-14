package domain.participant;

public class Player extends Participant {

    private final PlayerName playerName;
    private final BetAmount betAmount;

    public Player(String name, BetAmount betAmount) {
        super();
        this.playerName = new PlayerName(name);
        this.betAmount = betAmount;
    }

    public int getBetAmount() {
        return betAmount.getBetAmount();
    }

    public String getName() {
        return playerName.name();
    }
}
