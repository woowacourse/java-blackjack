package blackjack.model.participants;

public class Dealer extends Participant {
    private static final int STANDARD = 16;

    @Override
    public boolean checkDrawCardState() {
        return cards.getScore() <= STANDARD;
    }
}
