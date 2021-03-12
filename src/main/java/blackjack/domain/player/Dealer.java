package blackjack.domain.player;

import blackjack.domain.card.Score;

public class Dealer extends Player {

    private static final int MIN_SCORE_TO_STAND = 17;

    public Dealer() {
        super("딜러", new Money(0));
    }

    public void calculateProfit(final Gambler gambler) {
        Money dealerProfit = gambler.inverseMoney();
        money = money.addMoney(dealerProfit);
    }

    public boolean ableToDraw() {
        return cards.totalScore().isLessThan(Score.of(MIN_SCORE_TO_STAND));
    }

    public boolean isBiggerThan(final Player player) {
        return cards.isBiggerThan(player.cards());
    }

    public boolean isLessThan(final Player player) {
        return cards.isLessThan(player.cards());
    }
}
