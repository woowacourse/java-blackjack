package blackjack.domain.gamer;

public class Player extends Gamer {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDrawCard() {
        return !handScore().isBusted();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}