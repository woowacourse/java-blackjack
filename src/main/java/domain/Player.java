package domain;

public class Player extends Participant {
    public Player(final Name name, final Hand hand) {
        super(name, hand);
        validateName(name);
    }

    public Player(final Name name) {
        this(name, new Hand());
    }

    private void validateName(final Name name) {
        if (name.getName().equals("딜러")) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean canHit() {
        return hand.isNotBust();
    }
}
