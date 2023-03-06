package domain.player;

public class Participant extends Player {
    public Participant(Name name) {
        super(name);
    }

    @Override
    public boolean isNameEqualTo(String playerName) {
        return getName().equals(playerName);
    }
}
