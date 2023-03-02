package blackjack.domain;

public class Player extends Participant {

    private final Name name;

    public Player(final String name, final Cards cards) {
        super(cards);
        this.name = new Name(name);
    }

    @Override
    public boolean isDrawable() {
        return !cards.isMaximumScore() && !cards.isTotalScoreOver();
    }

    @Override
    public String getName() {
        return name.getValue();
    }
}
