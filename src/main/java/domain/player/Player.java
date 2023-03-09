package domain.player;

public final class Player extends Participant{

    private static final int BLACK_JACK_NUMBER = 21;

    private final Name name;

    public Player(final String name) {
        super(new Hand());
        this.name = new Name(name);
    }

    public boolean isEqualOrLargerThanBlackJackNumber() {
        if (getScore() >= BLACK_JACK_NUMBER) {
            return true;
        }
        return false;
    }

    public String getName() {
        return name.getName();
    }
}
