package blackjack.domain;

public class Player extends Participant {

    public Player(String name) {
        super(name, new Hand());

    }

    @Override
    public void recieveCard(Card card) {
        if (!isBust()) {
            addCard(card);
        }
    }

    public boolean shoudDraw() {
        return !isBust();
    }


}
