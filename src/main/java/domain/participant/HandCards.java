package domain.participant;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    private static final int BUST_CONDITION = 22;
    private final List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public HandCards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void drawCard(Card card) {
        cards.add(card);
    }

    public List<String> getCardNames() {
        List<String> cardList = new ArrayList<>();

        for (Card card : cards) {
            cardList.add(card.getCardName());
        }

        return cardList;
    }

    public int calculateCardsScore(){
        int aceCount = countAce();
        int score = calculateCardsScoreExceptAce() + aceCount;

        while(aceCount > 0) {
            score += determineAce(score);
            aceCount--;
        }

        return score;
    }

    public int getHandCardsSize() {
        return cards.size();
    }

    private int calculateCardsScoreExceptAce() {
        int score = 0;

        for(Card card : cards) {
            if (!card.isAce()) {
                score += card.getValue();
            }
        }

        return score;
    }

    private int countAce() {
        int aceCount = 0;

        for(Card card : cards) {
            if (card.isAce()) {
                aceCount++;
            }
        }

        return aceCount;
    }

    private int determineAce(int currentSum){
        if (currentSum + 10 < BUST_CONDITION) {
            return 10;
        }

        return 0;
    }
}
