package domain.member;

import domain.card.Card;
import java.util.List;

public class PlayerRole implements MemberRole {

    private BettingAmount amount;

    public PlayerRole(BettingAmount amount) {
        this.amount = amount;
    }

    @Override
    public List<Card> showFirstCards(List<Card> cards) {
        return cards;
    }

    @Override
    public void applyBonus() {
        amount = amount.applyBonus();
    }

    @Override
    public int getBettingAmount() {
        return amount.getAmount();
    }
}
