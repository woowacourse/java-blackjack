package blackjack.domain.player;

public class Player extends User {

    private final Name name;

    public Player(Name name) {
        this.name = name;
    }

    public String getPlayerName() {
        return name.getName();
    }
}
