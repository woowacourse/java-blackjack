package domain;

import java.util.ArrayList;
import java.util.List;

public class CardGroup {

    private final List<Card> cards;

    public CardGroup() {
        cards = new ArrayList<>();
    }

    public CardGroup(final List<Card> cards) {
        this.cards = cards;
    }

    public int calculateScoreWithOutAce() {
        return cards.stream()
                .filter(card -> card.getScore() != CardScore.ACE)
                .mapToInt(card -> card.getScore().cardScore)
                .sum();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int countCards() {
        return cards.size();
    }

    private int countAce(){
        return Math.toIntExact(cards.stream()
                .filter(card -> card.getScore() == CardScore.ACE)
                .count());
    }

    public int calculateScoreWithAce(int limit) {
        int calculateScoreWithOutAce = calculateScoreWithOutAce();
        int aceCount = countAce();

        int sum = calculateScoreWithOutAce + aceCount;
        while(aceCount-- > 0){
            int temp = sum + 10;
            if(temp > limit) break;
            sum = temp;
        }

        return sum;
    }
}
