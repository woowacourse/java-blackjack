package blackjack.domain;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    public Cards pickOpenCardsInInitCards() {
        return Cards.generateCardsAndFill(getMyCards());
    }
}
