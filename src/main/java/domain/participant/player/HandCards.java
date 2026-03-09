package domain.player;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    private List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<String> cardsToString() {
        List<String> cardList = new ArrayList<>();

        for (Card card : cards) {
            cardList.add(card.toString());
        }

        return cardList;
    }

    public int calculateCards(){
        int sum = 0;
        int aceCount = 0;


        for(Card card : cards) {
            if (card.isAce()) {
                aceCount++;
                continue;
            }

            sum += card.getValue();
        }

        while(aceCount > 0) {
            sum += calculateAce(sum);
            aceCount--;
        }

        return sum;
    }

    private int calculateAce(int currentSum){
        int aceCase10 = Math.max(21 - (currentSum + 11), 0);
        int aceCase1 = Math.max(21 - (currentSum + 11), 0);

        if (aceCase10 < aceCase1) {
            return 10;
        }

        return 1;
    }
}
