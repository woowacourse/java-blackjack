package blackjack.domain.participant;

public final class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public boolean hasNeverDrawn(int compare) {
        return getCardsName().size() <= compare;
    }

    @Override
    public boolean canDraw() {
        return !isBurst() && !isBlackjack();
    }

}
