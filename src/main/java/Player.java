public class Player extends Gamer {

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public boolean canReceiveAdditionalCards() {
        return !isBust();
    }
}
