package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getCount() {
        return cards.size();
    }

    public int getTotalPoint() {
        int totalPoint = 0;
        for(Card card : cards) {
            totalPoint += card.getCardPoint();
        }

        while(getAceCount() > 0 && totalPoint > 21) {
            totalPoint -= 10;
        }

        return totalPoint;
    }

    private int getAceCount() {
        int aceCount = 0;
        for(Card card : cards) {
            if(card.isAce()) {
                aceCount += 1;
            }
        }
        return aceCount;
    }

    public boolean isBust() {
        return getTotalPoint() > 21;
    }

    public boolean isOver17(){
        return getTotalPoint() >=17;
    }

    public String getFirstCardName(){
        return cards.getFirst().getName();
    }

    public String getCardNames(){

        List<String> names = new ArrayList<>();
        for(Card card:cards){
            names.add(card.getName());
        }

        return String.join(", ", names);
    }


}
