package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.gamer.Name;
import domain.gamer.Player;
import exception.BetAmountRangeException;
import exception.BetAmountUnitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetAmountsTest {
    Player player;
    BetAmounts betAmounts;

    @BeforeEach
    void init() {
        Name name = new Name("kaki");
        player = new Player(name);

        betAmounts = new BetAmounts();
    }

    @DisplayName("배팅 금액 범위 테스트")
    @Nested
    class BetAmountRangeTest {

        @DisplayName("배팅은 금액이 1,000원 미만이거나 1,000,000원을 초과할 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {900, 9999900})
        void createFailWithAmountRange(int amount) {

            assertThatThrownBy(() -> betAmounts.addBet(player, amount))
                    .isInstanceOf(BetAmountRangeException.class)
                    .hasMessage(BetAmountRangeException.INVALID_AMOUNT_RANGE);
        }

        @DisplayName("배팅은 금액이 1,000원 이상이거나 1,000,000원 이하일 경우 정상적으로 생성된다.")
        @ParameterizedTest
        @ValueSource(ints = {1000, 1000000})
        void createSuccessWithAmountRange(int amount) {

            assertThatCode(() -> betAmounts.addBet(player, amount))
                    .doesNotThrowAnyException();
        }
    }

    @DisplayName("배팅 금액 단위 테스트")
    @Nested
    class BetAmountUnitTest {

        @DisplayName("배팅은 금액의 단위가 100단위가 아닐 경우 예외가 발생한다.")
        @Test
        void createFailWithAmountUnit() {

            assertThatThrownBy(() -> betAmounts.addBet(player, 1010))
                    .isInstanceOf(BetAmountUnitException.class)
                    .hasMessage(BetAmountUnitException.INVALID_BET_AMOUNT_UNIT);
        }

        @DisplayName("배팅은 금액이 100으로 나뉘어 진다면 정상적으로 생성된다.")
        @Test
        void createSuccessWithAmountUnit() {

            assertThatCode(() -> betAmounts.addBet(player, 1100))
                    .doesNotThrowAnyException();
        }
    }
}
