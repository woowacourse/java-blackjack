package domain;

public class Player extends Gamer {
    private final String username;

    private static final int BUST_THRESHOLD = 21;

    public Player(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public boolean canGetMoreCard() {
        return this.canGetMoreCard(BUST_THRESHOLD);
    }

}
