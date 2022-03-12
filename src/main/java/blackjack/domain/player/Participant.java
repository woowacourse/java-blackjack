package blackjack.domain.player;

public class Participant extends Player {

    public Participant(final String name) {
        super(name);
    }

    @Override
    public boolean canAddCard() {
        return getTotalScore() <= 21;
    }
}
