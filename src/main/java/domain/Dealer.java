package domain;

public class Dealer extends Participant  {

    protected Dealer() {
        super("딜러");
    }

    @Override
    boolean ableToAddCard() {
        int cardsScore = cards.calculateScore();
        return cardsScore <= 16;
    }
}
