package blackjack.domain.participant;

public class Player extends Participant {

    private boolean stand;

    public Player(String nickname) {
        super(nickname);
        this.stand = false;
    }

    @Override
    public boolean isDrawable() {
        return !stand && super.isDrawable();
    }

    public void stand() {
        stand = true;
    }
}
