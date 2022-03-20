package blackjack.domain.user;

import blackjack.domain.PlayerResult;
import blackjack.domain.card.Cards;

public class Player extends User {

    private final Money money;

    public Player(String name, Money money, Cards cards) {
        super(name, cards);
        this.money = money;
    }

    public double calculateProfit(Dealer dealer) {
        PlayerResult playerResult = PlayerResult.valueOf(dealer, this);
        return money.calculate(playerResult.getProfit());
    }

    @Override
    public boolean isHit() {
        return !cards.isBust();
    }

}
