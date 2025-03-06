package domain;

public class Dealer extends Gamer {
    public Dealer(final Nickname nickname) {
        super(nickname);
    }

    public boolean canHit(int threshold) {
        int sumOfRank = hand.getSumOfRank();
        if (hand.hasAce()) {
            return sumOfRank + 10 <= threshold;
        }
        return sumOfRank <= threshold;
    }

    @Override
    public int calculateSumOfRank() {
        final int sumOfRank = hand.getSumOfRank();
        if (hand.hasAce()) {
            return sumOfRank + 10;
        }
        return sumOfRank;
    }
}
