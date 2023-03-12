package blackjack.domain.participant;

public class Player extends Participant {
    private final int betting;
    private double rate;
    public Player(final Name name, final int betting) {

        super(name);
        this.betting = betting;
    }

    public int getBetting(){return this.betting;}
    public void setRate(double rate){
        this.rate=rate;
    }
    public int getRevenue(){
        return (int) (rate * betting);
    }
}
