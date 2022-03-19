package blackjack;

import blackjack.domain.card.Deck;
import blackjack.domain.strategy.ShuffledDeckGenerateStrategy;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.vo.BettingMoney;
import blackjack.domain.vo.Name;

public class TestUtils {

    private TestUtils(){
    }

    private static final int MINIMUM_BETTING_AMOUNT = 10;

    public static Player createPlayerByName(String name) {
        return Player.from(Name.of(name), new BettingMoney(MINIMUM_BETTING_AMOUNT));
    }

    public static Dealer createDealer() {
        return Dealer.create();
    }

    public static Deck createDeck() {
        return new Deck(new ShuffledDeckGenerateStrategy());
    }
}
