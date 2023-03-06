package blackjack.domain;

public class Player extends Participant {

    private final int BUST_POINT = 21;

    private final Name name;

    Player(final String name) {
        this.name = new Name(name);
    }

    @Override
    public boolean isDrawable() {
        final int currentScore = currentScore();
        return currentScore < BUST_POINT;
    }

    public String getName() {
        return name.getName();
    }

    boolean hasName(final String playerName) {
        return name.getName()
                .equals(playerName);
    }
}
