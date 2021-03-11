package blackjack.domain;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    public void stay() {
        this.state = state.stay();
    }
}
