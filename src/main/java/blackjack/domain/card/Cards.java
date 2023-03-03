package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private final List<Card> cards;

    public Cards() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public int calculateTotalScore() {
        int totalScore = makeTotalScore();
        int aceCount = countAce();
        while(totalScore>21 && aceCount!=0){
            totalScore-=10;
        }
        return totalScore;
    }
    public Card getFirstCard(){
        return this.cards.get(0);
    }
    private int countAce(){
        return (int) cards.stream().filter(card->card.isAce()).count();
    }

    private int makeTotalScore(){
        return this.cards.stream().
                map(Card::getValue).
                reduce(0,Integer::sum);
    }
}
