package blackjack.domain.player;

public class Dealer extends Participant {

    public Dealer(String name) {
        super(name);
    }

    public boolean canDraw() {
        return cards.getScore() <= 16;
    }

}
