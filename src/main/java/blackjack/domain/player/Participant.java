package blackjack.domain.player;
import blackjack.domain.result.Result;

public class Participant extends Player {

    private final AcceptStrategy acceptStrategy;

    public Participant(final String name, final AcceptStrategy acceptStrategy) {
        super(name);
        this.acceptStrategy = acceptStrategy;
    }

    private boolean isUnderMaxScore() {
        return cards.calculateEndScore() <= Result.BLACKJACK_SCORE;
    }

    private boolean acceptCard() {
        return acceptStrategy.accept(this.name);
    }

    @Override
    public boolean acceptableCard() {
        return isUnderMaxScore() && acceptCard();
    }

    @Override
    public boolean isParticipant() {
        return true;
    }
}
