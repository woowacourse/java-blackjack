package domain.user;

public class Player extends User {

    private static final int BLACK_JACK_SCORE = 21;

    private final PlayerName name;

    public Player(final String name) {
        this.name = new PlayerName(name);
    }

    public boolean isRightName(final String name) {
        return new PlayerName(name).equals(this.name);
    }

    @Override
    public boolean isHittable() {
        return cards.isUnder(BLACK_JACK_SCORE);
    }

    public String getName() {
        return name.getName();
    }
}
