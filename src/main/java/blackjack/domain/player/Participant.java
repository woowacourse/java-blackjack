package blackjack.domain.player;

public class Participant extends Player {

    private final AcceptStrategy acceptStrategy;

    public Participant(final String name, final AcceptStrategy acceptStrategy) {
        super(name);
        this.acceptStrategy = acceptStrategy;
    }

    private boolean acceptCard() {
        return acceptStrategy.accept(this.name);
    }

    @Override
    public boolean acceptableCard() {
        return !cards.isBust() && acceptCard();
    }

    @Override
    public boolean isParticipant() {
        return true;
    }
}
