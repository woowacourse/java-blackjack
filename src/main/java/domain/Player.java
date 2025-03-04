package domain;

public class Player extends Gamer {
    private final String username;

    public Player(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
