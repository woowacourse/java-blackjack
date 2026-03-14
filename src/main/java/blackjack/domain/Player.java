package blackjack.domain;

public class Player extends Participant {

    private final BetAmount betAmount;

    public Player(String name, BetAmount betAmount) {
        super(name, new Hand());
        this.betAmount = betAmount;
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
        return betAmount.getAmount();
    }

}
