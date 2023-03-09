package domain.user;

import domain.CardHand;
import domain.Name;

public class Player extends AbstractUser {

    private final Name name;

    public Player(String nameValue) {
        this.name = new Name(nameValue);
    }

    public Player(String nameValue, CardHand cardHand) {
        super(cardHand);
        this.name = new Name(nameValue);
    }

    public String getNameValue() {
        return this.name.getValue();
    }


    @Override
    public boolean canAdd() {
        return super.calculateScore() < BLACKJACK_SCORE;
    }
}
