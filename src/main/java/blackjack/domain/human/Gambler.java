package blackjack.domain.human;

public class Gambler extends Player {

    private final Name name;

    private Gambler(Name name) {
        this.name = name;
    }

    public static Gambler of(Name name) {
        return new Gambler(name);
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
