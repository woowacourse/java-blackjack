package blackjack.domain;

public class Dealer extends Gamer{

    private Dealer(String name) {
        super(name);
    }

    public Dealer() {
        super("딜러");
    }
}
