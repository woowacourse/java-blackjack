package participant;

public class Dealer extends Participant {
    private static final int STAY = 16;
    private static final String NAME = "딜러";

    public Dealer() {
        super();
    }

    @Override
    public boolean ableToDraw() {
        return getScore() <= STAY;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public String getNickname() {
        return NAME;
    }

}
