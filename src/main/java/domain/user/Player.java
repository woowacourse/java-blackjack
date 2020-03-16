package domain.user;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    public boolean isDrawable() {
        return !handCard.isOver();
    }
}
