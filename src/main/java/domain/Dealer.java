package domain;

public class Dealer extends Participants {

    public Card getUpCard() {
        return cards.getFirst();
    }
}
