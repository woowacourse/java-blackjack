package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> holdCards;

    public Player(String name, List<Card> holdCards) {
        this.name = name;
        this.holdCards = holdCards;

    }

    public void addCard(Card card) {
        holdCards.add(card);
    }

    public int calculateTotalScore() {
        int results = 0;
        for (Card holdCard : holdCards) {
            results += holdCard.getScore();
        }

        boolean isAceExist = holdCards.stream()
                .anyMatch(holdCard -> holdCard.getCardNumber() == CardNumber.ACE);
        if (isAceExist && (results + 10) <= 21) {
            return results + 10;
        }
        return results;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHoldCards(){
        return List.copyOf(holdCards);
    }

    public boolean isBust() {
        return calculateTotalScore() > 21;
    }

    public GameResult compareScore(int dealerScore) {
        if(isBust() && dealerScore > 21) {
            return GameResult.DRAW;
        }
        if(isBust() && dealerScore <= 21) {
            return GameResult.LOSE;
        }

        if(!isBust() && dealerScore > 21) {
            return GameResult.WIN;
        }
        if(calculateTotalScore() > dealerScore) {
            return GameResult.WIN;
        }
        if(calculateTotalScore() == dealerScore) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }
}
