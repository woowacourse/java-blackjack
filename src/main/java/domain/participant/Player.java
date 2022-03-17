package domain.participant;

public class Player extends Participant {

    private final Money money;

    Player(String name, int money) {
        super(name);
        this.money = new Money(money);
    }

    @Override
    public boolean canDrawCard() {
        return cards.calculateScore() < MAX_SCORE;
    }

    public int getMoney() {
        return money.getValue();
    }
}
