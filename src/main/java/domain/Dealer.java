package domain;

public class Dealer extends Participant  {

    protected Dealer(String name) {
        super(name);
    }

    @Override
    boolean ableToAddCard() {
        int cardsScore = cards.calculateScore();
        return cardsScore <= 16;
    }
}
