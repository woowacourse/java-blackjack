package domain;

public class Dealer extends Participant {

    private final Name name;

    public Dealer() {
        this.name = new Name("딜러");
    }

    public String getName() {
        return name.getValue();
    }

    public Card getFirstCard() {
        return super.playerCards.getFirst();
    }
}
