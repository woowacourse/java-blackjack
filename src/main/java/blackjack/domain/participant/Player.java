package blackjack.domain.participant;

public class Player extends Participant {
    private boolean win = true;

    public Player(final String name) {
        super(name);
    }

    public void lose() {
        this.win = false;
    }

    public boolean getWin() {
        return win;
    }
}
