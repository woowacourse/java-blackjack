package domain.betting;

import static org.junit.jupiter.api.Assertions.*;

import domain.game.GameResult;
import domain.participant.Name;
import domain.participant.Player;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class CalculateProfitTest {

    @Test
    void 참가자가_패배하면_수익은_배팅금액만큼_감소한다() {
        Name name = new Name("pobi");
        Player player = new Player(name);
        Map<Name, BettingAmount> amountMap = new HashMap<>();
        amountMap.put(player.getName(), new BettingAmount(BigDecimal.valueOf(1000)));
        BettingAmounts bettingAmounts = new BettingAmounts(amountMap);
        CalculateProfit calculateProfit = new CalculateProfit(bettingAmounts);
        BigDecimal expected = BigDecimal.valueOf(-1000);
        BigDecimal actual = calculateProfit.calculate(name, GameResult.LOSE).getMoney();
        assertEquals(0, expected.compareTo(actual));
    }

    @Test
    void 참가자가_승리하면_수익은_배팅금액만큼_증가한다() {
        Name name = new Name("pobi");
        Player player = new Player(name);
        Map<Name, BettingAmount> amountMap = new HashMap<>();
        amountMap.put(player.getName(), new BettingAmount(BigDecimal.valueOf(1000)));
        BettingAmounts bettingAmounts = new BettingAmounts(amountMap);
        CalculateProfit calculateProfit = new CalculateProfit(bettingAmounts);
        BigDecimal expected = BigDecimal.valueOf(1000);
        BigDecimal actual = calculateProfit.calculate(name, GameResult.WIN).getMoney();
        assertEquals(0, expected.compareTo(actual));
    }

    @Test
    void 참가자가_블랙잭이면_수익은_배팅금액의_1_5배가_된다() {
        Name name = new Name("pobi");
        Player player = new Player(name);
        Map<Name, BettingAmount> amountMap = new HashMap<>();
        amountMap.put(player.getName(), new BettingAmount(BigDecimal.valueOf(1000)));
        BettingAmounts bettingAmounts = new BettingAmounts(amountMap);
        CalculateProfit calculateProfit = new CalculateProfit(bettingAmounts);
        BigDecimal expected = BigDecimal.valueOf(1500);
        BigDecimal actual = calculateProfit.calculate(name, GameResult.BLACKJACK).getMoney();
        assertEquals(0, expected.compareTo(actual));
    }

    @Test
    void 참가자가_비기면_수익은_0원이다() {
        Name name = new Name("pobi");
        Player player = new Player(name);
        Map<Name, BettingAmount> amountMap = new HashMap<>();
        amountMap.put(player.getName(), new BettingAmount(BigDecimal.valueOf(1000)));
        BettingAmounts bettingAmounts = new BettingAmounts(amountMap);
        CalculateProfit calculateProfit = new CalculateProfit(bettingAmounts);
        BigDecimal expected = BigDecimal.valueOf(0);
        BigDecimal actual = calculateProfit.calculate(name, GameResult.DRAW).getMoney();
        assertEquals(0, expected.compareTo(actual));
    }
}
