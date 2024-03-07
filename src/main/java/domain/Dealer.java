package domain;

public class Dealer extends Participant {

    public Dealer() {
        super(new Name("딜러"));
    }

    public boolean shouldHit() {
        return hands.calculateScore() <= 16;
    }
}
