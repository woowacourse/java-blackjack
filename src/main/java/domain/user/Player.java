package domain.user;

import domain.BettingAmount;
import domain.CardHand;
import domain.Name;

public class Player extends AbstractUser {

    private final Name name;

    private final BettingAmount bettingAmount;

    public Player(String nameValue, int bettingAmount) {
        this.name = new Name(nameValue);
        this.bettingAmount = new BettingAmount(bettingAmount);
    }

    public Player(String nameValue, int bettingAmount, CardHand cardHand) {
        super(cardHand);
        this.name = new Name(nameValue);
        this.bettingAmount = new BettingAmount(bettingAmount);
    }

    public String
    getNameValue() {
        return this.name.getValue();
    }

    public Name getName() {
        return this.name;
    }

    @Override
    public boolean canAdd() {
        return super.calculateScore() < BLACKJACK_SCORE;
    }

    public BettingAmount getBettingAmount() {
        return bettingAmount;
    }
}
