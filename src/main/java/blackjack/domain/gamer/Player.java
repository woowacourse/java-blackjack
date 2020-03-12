package blackjack.domain.gamer;

public class Player extends Gamer {

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
