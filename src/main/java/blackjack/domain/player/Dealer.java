package blackjack.domain.player;

public class Dealer extends Player {

    private static final int ADD_CARD_CONDITION = 16;

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean acceptableCard() {
        return cards.calculateScore() <= ADD_CARD_CONDITION;
    }

    @Override
    public boolean isParticipant() {
        return false;
    }
}
