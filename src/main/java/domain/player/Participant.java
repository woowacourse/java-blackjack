package domain.player;

public class Participant extends Player {
    public Participant(String name) {
        super(name);
    }
    
    @Override
    public boolean isDealer() {
        return false;
    }
    
    @Override
    public double calculateProfit(double betAmount) {
        return getState().calculateProfit(betAmount);
    }
    
    @Override
    public boolean isFinished() {
        return getState().isFinished();
    }
}
