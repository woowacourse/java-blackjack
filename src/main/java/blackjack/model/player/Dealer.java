package blackjack.model.player;

public class Dealer extends Player {

    public boolean isDrawable() {
        return calculatePoint() <= 16;
    }
}
