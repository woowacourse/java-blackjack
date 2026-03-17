package domain.participant;

public class Betting {
    private static final double BLACKJACK_REWARD = 1.5;
    private static final Betting ZERO = new Betting(0);

    private final int betting;

    public Betting(int betting) {
        this.betting = betting;
    }

    public Betting blackjack(){
        return new Betting((int) (betting * BLACKJACK_REWARD));
    }

    public Betting draw(){
        return ZERO;
    }

    public Betting lose(){
        return new Betting(-betting);
    }

    public int getBettingMoney(){
        return betting;
    }
}
