package domain.fixture;

import domain.card.CardArea;
import domain.player.BettingMoney;
import domain.player.Gambler;
import domain.player.Name;

public class GamblerFixture {

    public static Gambler 말랑(final CardArea cardArea) {
        return new Gambler(Name.of("말랑"), cardArea, BettingMoney.of(10000));
    }

    public static Gambler 콩떡(final CardArea cardArea) {
        return new Gambler(Name.of("콩떡"), cardArea, BettingMoney.of(10000));
    }

    public static Gambler 코다(final CardArea cardArea) {
        return new Gambler(Name.of("코다"), cardArea, BettingMoney.of(10000));
    }

    public static Gambler gamblerWithMoneyAndCardArea(final BettingMoney bettingMoney, final CardArea cardArea) {
        return new Gambler(Name.of("말랑"), cardArea, bettingMoney);
    }
}
