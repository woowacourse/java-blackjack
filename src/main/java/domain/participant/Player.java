package domain.participant;

import domain.BettingMoney;
import domain.Name;
import domain.constant.Result;
import java.util.List;

public class Player extends Participant {
    private final BettingMoney bettingMoney;

    public Player(Name name, BettingMoney bettingMoney) {
        super(name);
        this.bettingMoney = bettingMoney;
    }

    public double calculateProceeds(Result result) {
        return bettingMoney.calculateProceeds(result);
    }

    @Override
    public boolean canDraw() {
        return !(isBust() || isNaturalBlackJack());
    }

    @Override
    public List<String> getInitialOpenCards() {
        return revealCards();
    }
}