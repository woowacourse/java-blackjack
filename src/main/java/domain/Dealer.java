package domain;

public class Dealer extends Player{

    public Dealer(String playerName, CardPool cardPool) {
        super(playerName, cardPool);
    }

    public boolean isHit(int threshold) {
        return sumCardPool() <= threshold;
    }
}
