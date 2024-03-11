package blackjack.domain.participant;

public class Player extends Participant {
    protected Player(final String name) {
        super(name);
    }

    public boolean isName(final String otherName) {
        return name.isSame(otherName);
    }
}
