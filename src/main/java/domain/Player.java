package domain;

public class Player extends Participant {

    protected Player(String name) {
        super(name);
    }

    @Override
    boolean ableToAddCard() {
        int cardsScore = cards.calculateScore();
        return cardsScore <= 21;
    }
}
