package blackjack.domain.player;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public boolean canDraw() {
        return cards.getScore() < 21;
    }

}
