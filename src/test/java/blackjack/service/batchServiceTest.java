package blackjack.service;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import blackjack.domain.PlayerResult;
import blackjack.domain.user.Bet;
import blackjack.domain.user.Player;
import blackjack.dto.UserProfitDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class batchServiceTest {

    @Test
    @DisplayName("블랙잭으로 우승하면 1.5배, 그냥 우승은 1배, 패배는 -1배, 무승부는 0을 얻는다. 딜러의 수익과 플레이어의 수익의 합은 0이다.")
    void calculateTest() {
        Cards blackJack = new Cards(List.of(new Card(Shape.CLOVER, Number.JACK), new Card(Shape.DIAMOND, Number.ACE)));
        Cards initCards = new Cards(List.of(new Card(Shape.CLOVER, Number.JACK), new Card(Shape.DIAMOND, Number.FIVE)));
        Map<Player, PlayerResult> statistics = new LinkedHashMap<>();
        statistics.put(new Player("giron", Bet.from(1000), blackJack), PlayerResult.BLACKJACK);
        statistics.put(new Player("test1", Bet.from(1000), initCards), PlayerResult.WIN);
        statistics.put(new Player("test2", Bet.from(1000), initCards), PlayerResult.LOSE);
        statistics.put(new Player("test3", Bet.from(1000), initCards), PlayerResult.DRAW);
        UserProfitDto calculate = batchService.calculate(statistics);
        Map<String, Double> playerProfit = calculate.getPlayerProfit();
        double dealerProfit = calculate.getDealerProfit();
        assertAll(
                () -> assertThat(playerProfit.get("giron")).isEqualTo(1500),
                () -> assertThat(playerProfit.get("test1")).isEqualTo(1000),
                () -> assertThat(playerProfit.get("test2")).isEqualTo(-1000),
                () -> assertThat(playerProfit.get("test3")).isEqualTo(0),
                () -> assertThat(dealerProfit).isEqualTo(-1500)
        );

    }
}
