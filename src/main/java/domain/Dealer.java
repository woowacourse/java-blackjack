package domain;

public class Dealer extends Gamer{
    public Dealer() {
        super(new Name("딜러"));
    }

    @Override
    public int hit(Deck deck) {
        int dealerDrawCount = 0;
        while (this.getTotalScore() <= 16) {
            hand.add(deck.draw());
            dealerDrawCount++;
        }
        return dealerDrawCount;
    }
}
