package blackjack.domain.user;

import blackjack.domain.user.status.Status;

public class User extends Participant {
    public User(String name) {
        super(name);
    }

    public void stopUser() {
        status = Status.STOP;
    }

    public boolean canContinueGame() {
        return status.canContinueGame();
    }
}
