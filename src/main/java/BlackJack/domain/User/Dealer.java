package BlackJack.domain.User;

import BlackJack.domain.Result;

import java.util.Arrays;
import java.util.List;

public class Dealer extends User {

    private static final int DEALER_ADD_CARD_LIMIT = 16;

    public Dealer() {
        super("딜러");
    }

    public void checkBlackJack(){
        List<String> openCardNumber = Arrays.asList("10","K","J","Q");
        if(openCardNumber.contains(cards.getDeck().get(0).getNumber().getDenomination())){
            if(cards.calculateScore() == 21){
                this.result = Result.BLACKJACK;
            }
        }
    }

//    public Result compare(Player player) {
//        if (player.getScore() > 21 || (this.getScore() > player.getScore() && this.getScore() <= 21)) {
//            return Result.LOSE;
//        }
//        if (this.getScore() > 21 || this.getScore() < player.getScore() && player.getScore() <= 21) {
//            dealerLoseCount++;
//            return Result.WIN;
//        }
//        dealerDrawCount++;
//        return Result.DRAW;
//    }
//
//    public int getDealerLoseCount() {
//        return dealerLoseCount;
//    }
//
//    public int getDealerDrawCount() {
//        return dealerDrawCount;
//    }

    boolean checkPossibleAdd(int currentScore) {
        return currentScore < DEALER_ADD_CARD_LIMIT;
    }
}
