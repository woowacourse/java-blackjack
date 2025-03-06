package domain;

public class Dealer extends Participant{

    private Dealer(Cards cards) {
        super(cards);
    }

    public static Dealer createEmpty() {
        return new Dealer(Cards.createEmpty());
    }

    public Card getFirstCard() {
        return cards.getFirst();
    }
}
