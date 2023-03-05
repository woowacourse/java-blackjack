package domain;

public class Player extends Participant{

    public Player(String name) {
        super(new Name(name), new Cards());
    }

    public boolean isOverPlayerBlackJack() {
        return super.getCardsSum() >= BlackjackGame.BLACK_JACK;
    }
}
