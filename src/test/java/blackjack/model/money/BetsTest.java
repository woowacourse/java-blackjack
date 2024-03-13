package blackjack.model.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.entry;

import blackjack.model.money.Bets;
import blackjack.model.money.Money;
import blackjack.model.participant.Player;
import blackjack.model.result.ResultCommand;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BetsTest {
    @ParameterizedTest
    @CsvSource(value = {"-1", "0"})
    @DisplayName("새로운 베팅 금액을 추가할 때 금액이 양의 정수가 아닌값이면 예외를 던진다.")
    void createMoneyByNotPositiveInteger(int money) {
        Bets bets = new Bets();
        Player player = new Player("리브");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> bets.addBet(player, money))
                .withMessage("0원 이하의 금액을 베팅할 수 없습니다.");
    }

    @Nested
    @DisplayName("수익률 계산")
    class calculateProfit {
        private Bets bets;
        private Player player;


        @BeforeEach
        void init() {
            bets = new Bets();
            player = new Player("리브");
        }

        @ParameterizedTest
        @CsvSource(value = {"WIN,10_000", "LOSE,-10_000", "DRAW,10_000", "BLACK_JACK_WIN,15_000"})
        @DisplayName("플레이어의 수익을 계산한다.")
        void calculatePlayerProfit(ResultCommand resultCommand, int expected) {
            bets.addBet(player, 10_000);
            Map<Player, ResultCommand> playerResultCommand = new LinkedHashMap<>(Map.of(player, resultCommand));
            assertThat(bets.calculatePlayersProfit(playerResultCommand))
                    .contains(entry(player, new Money(expected)));
        }

        @ParameterizedTest
        @CsvSource(value = {"WIN,-10_000", "LOSE,10_000", "DRAW,-10_000", "BLACK_JACK_WIN,-15_000"})
        @DisplayName("딜러의 수익을 계산한다.")
        void calculateDealerProfit(ResultCommand resultCommand, int expected) {
            bets.addBet(player, 10_000);
            Map<Player, ResultCommand> playerResultCommand = new LinkedHashMap<>(Map.of(player, resultCommand));
            assertThat(bets.calculateDealerProfit(playerResultCommand)).isEqualTo(new Money(expected));
        }
    }
}
