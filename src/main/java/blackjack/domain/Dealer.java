package blackjack.domain;

public class Dealer extends Participant {

    public Dealer() {
        super();
    }

    @Override
    public boolean isPossibleToAdd() {
        return false;
    }
}
