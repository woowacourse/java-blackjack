package domain;

public class Dealer extends Participant{

    public Dealer(String name) {
        super(name);
    }

    public Card getInitialCard() {
        return getCards().getFirst();
    }
}
