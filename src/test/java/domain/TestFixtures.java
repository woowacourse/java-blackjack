package domain;

import domain.card.CardHand;
import domain.game.BettingMoney;
import domain.game.Gamblers;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestFixtures {

    private static long id = 0L;

    public static Player playerOfDefaultHand() {
        String name = String.format("플레이어%d", ++id);
        return new Player(name, new CardHand());
    }

    public static Gamblers gamblers(Dealer dealer, List<Player> players) {
        Map<Player, BettingMoney> map = new LinkedHashMap<>();
        BettingMoney bettingMoney = BettingMoney.of(10000);

        for (Player player : players) {
            map.put(player, bettingMoney);
        }
        return new Gamblers(dealer, map);
    }
}
