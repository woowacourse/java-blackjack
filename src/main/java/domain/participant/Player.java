package domain.participant;

public class Player extends Participant {

    private final PlayerName playerName;
    private BetAmount betAmount;

    public Player(String name) {
        super();
        this.playerName = new PlayerName(name);
    }

    public String getName() {
        return playerName.name();
    }

    public void setBetAmount(BetAmount betAmount) {
        this.betAmount = betAmount;
    }
}
