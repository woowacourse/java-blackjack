package domain.player;

public class Participant extends Player {
    public Participant(String name) {
        super(name);
    }
    
    @Override
    public boolean isNameEqualTo(String playerName) {
        return getName().equals(playerName);
    }
    
    @Override
    public boolean isDealer() {
        return false;
    }
}
