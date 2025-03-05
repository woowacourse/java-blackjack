package blackjack.domain;

public class Dealer extends Participant {

    public Dealer() {
        super();
    }

    public Card openFirstCard() {
        return super.cards.getFirst();
    }

    @Override
    public boolean isPossibleToAdd() {
        return super.calculateDenominations() <= 16;
    }
}
