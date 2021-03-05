package blackjack.domain.user;

public class User extends Participant {
    public User(Name name) {
        super(name);
    }

    public void stopUser(){
        status = Status.STOP;
    }

    public boolean canContinueGame() {
        return status.canContinueGame();
    }
}
