package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> holdCards;

    public String getName() {
        return name;
    }

    private final String name;

    public Player(String name) {
        this.holdCards = new ArrayList<>();
        this.name = name;
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

    public List<Card> getHoldCards(){
        return List.copyOf(holdCards);
    }

    public boolean isBust(int playerTotalScore) {
        return playerTotalScore > 21;
    }
}
