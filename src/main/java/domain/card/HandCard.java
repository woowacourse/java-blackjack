package domain.card;

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
        int nonAceTotal = cards.stream().map(Card::getRankScore).filter(e -> e != 1).mapToInt(Integer::intValue).sum();
        int aceCnt = (int) cards.stream().map(Card::getRankScore).filter(e -> e == 1).count();

        int result = aceCalculator(nonAceTotal, aceCnt);

        if(result > BLACKJACK_MAX_LIMIT) return 0;

        return result;
    }

    private int aceCalculator(int nonAceTotal, int aceCnt){
        int totalSum = nonAceTotal + (aceCnt*ACE_MAX_VALUE);
        int remainingAce = aceCnt;
        while(totalSum > BLACKJACK_MAX_LIMIT && remainingAce > 0){
           totalSum -= (ACE_MAX_VALUE - ACE_MIN_VALUE);
           remainingAce--;
        }
        return totalSum;
    }
    /*
    private int aceCalculator(int sum, int ace_cnt){
        return Math.min(ace_cnt, Math.max(0, (BLACKJACK_MAX_LIMIT - sum) / (ACE_MAX_VALUE - ACE_MIN_VALUE)));
    }
     */

    public void addCard(Card card){
        cards.add(card);
    }
}
