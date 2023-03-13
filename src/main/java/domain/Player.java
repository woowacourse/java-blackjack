package domain;

public class Player extends Participant {

    public static final int STANDARD_SUM_OF_BLACKJACK = 21;

    public Player(Name name, Cards cards, Money money) {
        super(name, cards, money);
    }

    @Override
    public boolean canReceiveOneMoreCard() {
        return cards.calculateSum() < STANDARD_SUM_OF_BLACKJACK;
    }
}
