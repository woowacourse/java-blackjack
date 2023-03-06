package domain.player;

public class Dealer extends Player {
    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean isNameEqualTo(String name) {
        return getName().equals(name);
    }
    
    @Override
    public boolean isDealer() {
        return true;
    }
}
