package blackjack.domain.player;

public class Player extends User {

    private final Name name;

    public Player(Name name) {
        super(name);
        this.name = name;
    }

    public String getPlayerName() {
        return name.getName();
    }
}
