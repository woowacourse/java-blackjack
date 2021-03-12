package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Stay;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitResultTest {

    ProfitResult profitResult;
    @BeforeEach
    void setUp() {
        Map<String, Double> result = new HashMap<>();
        result.put("pika", 10000.0);
        result.put("air", 20000.0);

        Map<Player, MatchResult> matchResult = new HashMap<>();
        matchResult.put(new Player("pika", new Blackjack(new Cards())), MatchResult.WIN);
        matchResult.put(new Player("air", new Stay(new Cards())), MatchResult.LOSE);

        Players players = new Players(Arrays.asList(new Player("pika"), new Player("air")));

        profitResult = new ProfitResult(result);
        profitResult.calculateProfit(matchResult, players);
    }

    @Test
    void calculateProfitTest() {
        assertThat(profitResult.getResult().get("pika")).isEqualTo(15000.0);
        assertThat(profitResult.getResult().get("air")).isEqualTo(-20000.0);
    }

    @Test
    void calculateDealerProfitTest() {
        assertThat(profitResult.calculateDealerProfit()).isEqualTo(5000);
    }
}
