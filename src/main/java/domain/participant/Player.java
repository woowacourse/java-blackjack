package domain.participant;

public class Player extends Participant {
    private int betAmount;

    public Player(String name) {
        super(name);
        this.betAmount = 0;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
