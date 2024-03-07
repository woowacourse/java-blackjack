package domain;

public class Dealer extends Player{

    public Dealer(final Name name) {
        super(name);
    }

    @Override
    public boolean alive() {
        return calculateScore() < 17;
    }
}
