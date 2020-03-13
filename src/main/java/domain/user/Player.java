package domain.user;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isAvailableToDraw() {
        return !cards.areBust() && !cards.areBlackJack() && !cards.areBlackJackPoint();
    }
}
