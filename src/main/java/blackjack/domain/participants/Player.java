package blackjack.domain.participants;

public class Player extends Participants {

    private boolean win = true;

    public Player(String name) {
        super(name);
    }

    public void lose() {
        win = false;
    }

    public boolean getWin() {
        return win;
    }
}
