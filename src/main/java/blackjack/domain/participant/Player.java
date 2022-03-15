package blackjack.domain.participant;

public class Player extends Participant {

    private final int bettingMoney;
    private boolean isTurnEnd = false;

    public Player(String name) {
        super(name);
        this.bettingMoney = 0;
    }

    public Player(String name, int bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    public boolean canHit() {
        return !isBlackjack() && !isBust() && !isTurnEnd;
    }

    public void stay() {
        isTurnEnd = true;
    }
}
