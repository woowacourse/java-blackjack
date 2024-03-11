package domain;

public class Dealer extends Gamer {
    public int drawAndGetCount(Deck deck) {
        int dealerDrawCount = 0;
        while (getTotalScore() <= 16) {
            takeCard(deck.draw());
            dealerDrawCount++;
        }
        return dealerDrawCount;
    }
}
