package domain.player;

public class Dealer extends Player {

    public boolean canStand() {
        int score = handCard.cardCalculator();
        return score >= 17 || score == 0;
    }
    public String showFirstCard() {
        return handCard.getFirstCardInfo();
    }
}
