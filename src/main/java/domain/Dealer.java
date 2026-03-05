package domain;

import java.util.List;

public class Dealer {
    private final List<Card> holdCards;

    public Dealer(List<Card> holdCards) {
        this.holdCards = holdCards;
    }

    public void addCard(Card card) {
        holdCards.add(card);
    }

    public boolean isReceiveCard() {
        return calculateTotalScore() <= 16;
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
