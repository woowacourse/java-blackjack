package domain.participant;

public class Player extends Participant {
    private static final int LOSS = -1;

    public Player(String name) {
        super(name);
    }

    public int getWinLoss(int dealerTotal) {
        if (isHandBust()) {
            return LOSS;
        }
        return Integer.compare(getHandTotal(), dealerTotal);
    }
}
