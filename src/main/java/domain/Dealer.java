package domain;

public class Dealer extends Gamer{
    public Dealer(CardGroup cardGroup) {
        super(cardGroup);
    }

    public boolean isLessThen(int score){
        return this.cardGroup.calculateScore(Gamer.LIMIT) <= score;
    }
}
