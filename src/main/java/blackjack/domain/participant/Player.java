package blackjack.domain.participant;

public class Player extends Participant {
    private final BattingAmount battingAmount;

    public Player(final String name, final BattingAmount battingAmount) {
        super(name);
        this.battingAmount = battingAmount;
    }

    public BattingAmount getBattingAmount() {
        return battingAmount;
    }

    @Override
    public boolean canReceiveCard() {
        return !isFinished();
    }
}
