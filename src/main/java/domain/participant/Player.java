package domain.participant;

public class Player extends Participant {

    private final PlayerName playerName;
    private BetAmount betAmount;

    public Player(String name) {
        super();
        this.playerName = new PlayerName(name);
    }

    public void setBetAmount(BetAmount betAmount) {
        this.betAmount = betAmount;
    }

    public int getBetAmount() {
        return betAmount.getBetAmount();
    }

    public String getName() {
        return playerName.name();
    }
}
