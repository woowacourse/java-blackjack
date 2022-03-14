package BlackJack.domain.User;

import BlackJack.domain.Result;

import java.util.Arrays;
import java.util.List;

public class Dealer extends User {

    private static final int DEALER_ADD_CARD_LIMIT = 16;

    public Dealer() {
        super("딜러");
    }

    public boolean checkBlackJack(){
        List<String> openCardNumber = Arrays.asList("10","K","J","Q");
        if(openCardNumber.contains(cards.getDeck().get(0).getNumber().getDenomination())){
            return this.isBlackJack();
        }
        return false;
    }


    public boolean isPossibleToAdd() {
        return this.getScore() < DEALER_ADD_CARD_LIMIT;
    }
}
