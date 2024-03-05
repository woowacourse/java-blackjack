public class Dealer extends Player{

    public Dealer() {}

    public boolean shouldHit() {
        return calculateScore() <= 16;
    }
}
