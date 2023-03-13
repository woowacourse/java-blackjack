package blackjack.domain.participants;

public class Player extends Participant {

    private final Name name;

    public Player(final String name) {
        this.name = new Name(name);
    }

    @Override
    public boolean isDrawable() {
        return !isBusted();
    }

    public String getName() {
        return name.getName();
    }

    public boolean hasName(final String playerName) {
        return name.getName()
                .equals(playerName);
    }

}
