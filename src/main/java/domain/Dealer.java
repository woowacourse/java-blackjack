package domain;

public class Dealer extends Player {

    public Dealer() {
        super(new Name("딜러"));
    }

    public boolean shouldHit() {
        return calculateScore() <= 16;
    }
}
