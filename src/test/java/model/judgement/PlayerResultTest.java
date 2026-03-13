package model.judgement;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.Map;
import model.paticipant.Player;
import org.junit.jupiter.api.Test;

class PlayerResultTest {

    @Test
    void 플레이어별_수익을_계산한다() {
        // given
        Player pobi = new Player("pobi", 10000);
        Player jason = new Player("jason", 20000);

        Map<Player, ResultStatus> result = new LinkedHashMap<>();
        result.put(pobi, ResultStatus.WIN);
        result.put(jason, ResultStatus.LOSE);

        PlayerResult playerResult = new PlayerResult(result);

        // when
        Map<Player, Profit> profits = playerResult.calculateProfits();

        // then
        assertThat(profits.get(pobi)).isEqualTo(new Profit(10000));
        assertThat(profits.get(jason)).isEqualTo(new Profit(-20000));
    }

    @Test
    void 딜러의_수익은_플레이어_수익의_합의_반대이다() {
        // given
        Player pobi = new Player("pobi", 10000);
        Player jason = new Player("jason", 20000);

        Map<Player, ResultStatus> result = new LinkedHashMap<>();
        result.put(pobi, ResultStatus.WIN);
        result.put(jason, ResultStatus.LOSE);

        PlayerResult playerResult = new PlayerResult(result);

        // when
        Profit dealerProfit = playerResult.calculateDealerProfit();

        // then
        assertThat(dealerProfit).isEqualTo(new Profit(10000));
    }

    @Test
    void 무승부인_플레이어의_수익은_0이다() {
        // given
        Player pobi = new Player("pobi", 10000);

        Map<Player, ResultStatus> result = new LinkedHashMap<>();
        result.put(pobi, ResultStatus.DRAW);

        PlayerResult playerResult = new PlayerResult(result);

        // when
        Map<Player, Profit> profits = playerResult.calculateProfits();

        // then
        assertThat(profits.get(pobi)).isEqualTo(Profit.ZERO);
    }
}
