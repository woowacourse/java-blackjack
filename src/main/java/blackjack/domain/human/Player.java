package blackjack.domain.human;

public class Player extends Human {

    private final Name name;

    private Player(Name name) {
        this.name = name;
    }

    public static Player of(Name name) {
        return new Player(name);
    }

    @Override
    public boolean isOneMoreCard() {
        return !isOverThanMaxPoint();
    }

    @Override
    public String getName() {
        return name.getName();
    }
}
