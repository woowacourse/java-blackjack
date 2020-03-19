package domain.gamer;

import domain.card.Card;
import domain.result.Result;
import domain.result.ResultDerivable;

import java.util.List;

public class Player extends AbstractGamer {
    private static final Money DEFAULT_MONEY = new Money(0);

    private Money bettingMoney;

    public Player(Name name) {
        this(name, new GamerHand(), DEFAULT_MONEY);
    }

    public Player(Name name, Money bettingMoney) {
        this(name, new GamerHand(), bettingMoney);
    }

    public Player(Name name, GamerHand gamerHand) {
        this(name, gamerHand, DEFAULT_MONEY);
    }

    public Player(Name name, GamerHand gamerHand, Money bettingMoney) {
        super(name, gamerHand);
        this.bettingMoney = bettingMoney;
    }

    @Override
    public List<Card> openInitialCards() {
        return super.openAllCards();
    }

    public Result determineResult(Dealer dealer, ResultDerivable resultDerivable) {
        return resultDerivable.derivePlayerResult(this, dealer);
    }

    public Money getBettingMoney() {
        return bettingMoney;
    }
}
