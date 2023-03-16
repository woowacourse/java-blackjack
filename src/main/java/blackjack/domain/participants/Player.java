package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.dto.HandStatus;
import java.util.List;

public class Player extends Participant {

    private final BettingMoney bettingMoney;

    public Player(final String name, final BettingMoney bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    public Player(final String name, final List<Card> cards, final BettingMoney bettingMoney) {
        super(name, cards);
        this.bettingMoney = bettingMoney;
    }

    @Override
    public boolean isHitAble() {
        return isNotBustNorHasMaxScore();
    }

    @Override
    public HandStatus toHandStatus() {
        return new HandStatus(getName(), cards());
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
