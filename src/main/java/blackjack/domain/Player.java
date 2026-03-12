package blackjack.domain;

public class Player extends Participant {

    private int betAmount = 0;

    public Player(String name) {
        super(name, new Hand());

    }

    @Override
    public void recieveCard(Card card) {
        addCard(card);
    }

    @Override
    public boolean shouldDraw() {
        return !isBust();
    }


    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }
}
