package blackjack.domain.participant;

public class Player extends Participant {
    private boolean win;

    public Player(final String name) {
        super(name);
        this.win = true;
    }

    public void lose() {
        this.win = false;
    }

    public boolean getWin() {
        return win;
    }
}
