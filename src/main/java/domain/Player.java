package domain;

public class Player extends Participant {

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    public void shouldReceive(Deck deck) {

    }
}
