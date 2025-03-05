package domain;

public class Player extends GameParticipant {

    private Player(Cards cards) {
        super(cards);
    }

    public static Player init() {
        return new Player(Cards.empty());
    }

    public static Player of(Cards cards) {
        return new Player(cards);
    }
}
