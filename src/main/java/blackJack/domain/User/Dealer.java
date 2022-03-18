package blackJack.domain.User;

import blackJack.utils.ExeptionMessage;

import java.util.Arrays;
import java.util.List;

public class Dealer extends User {

    private static final int DEALER_ADD_CARD_LIMIT = 16;

    public Dealer() {
        super("딜러");
    }

    public boolean isPossibleToAdd() {
        if(this.getScore() < DEALER_ADD_CARD_LIMIT){
            return true;
        }
        return false;
    }

    public Object getFirstCard() {
        return cards.getFirst();
    }
}
