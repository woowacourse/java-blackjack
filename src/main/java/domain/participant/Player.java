package domain.participant;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public Player(String name, HandCards handCards) {
        super(name, handCards);
    }

}
