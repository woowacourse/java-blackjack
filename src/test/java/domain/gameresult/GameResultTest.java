package domain.gameresult;

import domain.card.Hand;
import domain.player.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameResultTest {
    @Test
    @DisplayName("플레이어와 그에 대응하는 베팅액 정보를 이용해 객체를 생성할 수 있다.")
    void generatingGameResult() {
        Map<Player, Bet> result = Map.of(
                new Gambler(Hand.withEmptyHolder(), Name.of("여우"), Bet.from(3000)), Bet.from(3000),
                new Dealer(Hand.withEmptyHolder()), Bet.from(10000)
        );
        assertDoesNotThrow(() -> GameResult.from(result));
    }

    @Test
    @DisplayName("플레이어 이름과 베팅 정보를 이용한 작업을 주입하여 사용할 수 있다.")
    void doLogicWithNameAndBet() {
        Map<Player, Bet> result = new LinkedHashMap<>();
        result.put(new Gambler(Hand.withEmptyHolder(), Name.of("여우"), Bet.from(3000)), Bet.from(3000));
        result.put(new Gambler(Hand.withEmptyHolder(), Name.of("아벨"), Bet.from(5000)), Bet.from(5000));
        GameResult gameResult = GameResult.from(result);

        List<String> names = new ArrayList<>();
        List<Integer> bets = new ArrayList<>();
        gameResult.doLogicWithNameAndBetValue((name, bet) -> {
            names.add(name);
            bets.add(bet);
        });

        assertThat(names).hasSize(2)
                .containsExactly("여우", "아벨");
        assertThat(bets).hasSize(2)
                .containsExactly(3000, 5000);
    }
}