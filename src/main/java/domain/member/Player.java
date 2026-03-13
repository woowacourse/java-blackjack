package domain.member;

import domain.card.Card;
import java.util.List;

public class Player extends Member {

    private BettingAmount amount;

    public Player(String name, int amount) {
        super(name);
        this.amount = new BettingAmount(amount);
    }

    @Override
    public List<Card> showFirstCards() {
        return super.handCards();
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    public void applyBlackjackBonus() {
        amount = amount.applyBonus();
    }

    public int getBettingAmount() {
        return amount.getAmount();
    }
}
