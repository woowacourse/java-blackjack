package domain;

import java.util.ArrayList;
import java.util.List;

public class HandCard {
    private final List<Card> cards;

    private static final int BLACKJACK_MAX_LIMIT = 21;
    private static final int ACE_MAX_VALUE = 11;
    private static final int ACE_MIN_VALUE = 1;

    public HandCard(){
        this.cards = new ArrayList<>();
    }
    
    public int cardCalculator(){
        int sum = cards.stream().map(Card::getRankScore).filter(e -> e != 1).mapToInt(Integer::intValue).sum();
        int ace_cnt = cards.stream().map(Card::getRankScore).filter(e -> e == 1).count();
        int result = sum + aceCalculator(sum, ace_cnt);

        if(result > BLACKJACK_MAX_LIMIT) return 0;
        
        return result;
    }

    private int aceCalculator(int sum, int ace_cnt){
        int totalSum = sum + (ace_cnt*ACE_MAX_VALUE);
        while(totalSum > BLACKJACK_MAX_LIMIT && ace_cnt > 0){
           totalSum -= (ACE_MAX_VALUE-ACE_MIN_VALUE);
           ace_cnt--;
        }
        return totalSum - sum;
    }

    public void addCard(Card card){
        cards.add(card);
    }
}
