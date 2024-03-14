package blackjack.domain;

public class Player {
    private final String name;
    private final State state;

    public Player(String name) {
        this.name = name;
        this.state = new InitialState();
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }
}
