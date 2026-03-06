package blackjack.model;

public class Dealer extends Participant {

    @Override
    public boolean canReceive(int score) {
        return score <= 16;
    }
}
