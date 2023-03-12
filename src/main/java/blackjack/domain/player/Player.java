package blackjack.domain.player;

public class Player extends User {

    public Player(Name name) {
        super(name);
    }

    public String getPlayerName() {
        return name.getName();
    }
}
