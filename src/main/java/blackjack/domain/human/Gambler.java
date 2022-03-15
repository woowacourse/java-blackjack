package blackjack.domain.human;

public class Gambler extends Player {

    private Gambler(Name name) {
        super(name);
    }

    public static Gambler of(Name name) {
        return new Gambler(name);
    }

    @Override
    public boolean isOneMoreCard() {
        return !isOverThanMaxPoint();
    }
}
