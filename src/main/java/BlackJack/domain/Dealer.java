package BlackJack.domain;

import BlackJack.dto.PlayerResultDto;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends User {

    private static final int DEALER_ADD_CARD_LIMIT = 16;

    private int dealerLoseCount = 0;
    private int dealerDrawCount = 0;

    public Dealer(Cards cards) {
        super("딜러", cards);
    }

    @Override
    public void addCard() {
        cards.add(CardFactory.drawOneCard());
    }

    public boolean checkScore(){
        return cards.calculateScore() <= DEALER_ADD_CARD_LIMIT;
    }

    public Result compare(Player player){
        if( player.getScore() > 21 || (this.getScore() > player.getScore() && this.getScore() <= 21)){
            return Result.LOSE;
        }
        if( this.getScore() > 21 || this.getScore() < player.getScore() && player.getScore() <= 21){
            dealerLoseCount++;
            return Result.WIN;
        }
        dealerDrawCount++;
        return Result.DRAW;
    }

    public int getDealerLoseCount() {
        return dealerLoseCount;
    }

    public int getDealerDrawCount() {
        return dealerDrawCount;
    }

}
