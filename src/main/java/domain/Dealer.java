package domain;

public class Dealer extends Gamer {
    public Dealer(final Nickname nickname) {
        super(nickname);
    }

    public boolean isMoreThanThreshold(int threshold) {
        return this.hand.getSumOfRank() >= threshold;
    }
}
