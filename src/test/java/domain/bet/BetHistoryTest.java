package domain.bet;

import static message.ErrorMessage.BETTING_MONEY_MUST_BE_MULTIPLE_OF_100;
import static message.ErrorMessage.BETTING_MONEY_NOT_AVAILABLE;
import static message.ErrorMessage.PLAYER_NOT_IN_BETTING;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import domain.participant.Name;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetHistoryTest {

    private Name firstPlayer;
    private Name secondPlayer;
    private BetHistory betHistory;

    @BeforeEach
    void set_up() {
        firstPlayer = new Name("피즈");
        secondPlayer = new Name("스타크");
        betHistory = new BetHistory(List.of(firstPlayer, secondPlayer));
    }

    @Nested
    @DisplayName("금액 배팅시의 예외 테스트")
    class exception {
        @DisplayName("게임에 참여한 플레이어만 값을 배팅할 수 없다.")
        @Test
        void 플레이어_금액_배팅_테스트() {
            Name unknownPlayer = new Name("신원미상");

            assertThatThrownBy(() -> betHistory.bettingMoney(unknownPlayer, 10_000))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(PLAYER_NOT_IN_BETTING.getMessage());
        }

        @DisplayName("플레이어는 양수 이외의 값을 배팅할 수 없다.")
        @ParameterizedTest
        @ValueSource(ints = {-10_000, 0})
        void 플레이어_금액_배팅_테스트_1(int bettingMoney) {
            assertThatThrownBy(() -> betHistory.bettingMoney(firstPlayer, bettingMoney))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BETTING_MONEY_NOT_AVAILABLE.getMessage());
        }

        @DisplayName("플레이어는 100원 단위의 배팅이 아닌 값을 배팅할 경우 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(ints = {130, 50, 230})
        void 플레이어_금액_배팅_테스트_2(int bettingMoney) {
            assertThatThrownBy(() -> betHistory.bettingMoney(firstPlayer, bettingMoney))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(BETTING_MONEY_MUST_BE_MULTIPLE_OF_100.getMessage());
        }
    }

    @DisplayName("금액을 정상 배팅하는 경우의 기능을 테스트합니다.")
    @Test
    void 금액_배팅_기능_테스트() {
        //given
        //when
        betHistory.bettingMoney(firstPlayer, 10_000);
        betHistory.bettingMoney(secondPlayer, 20_000);
        //then
        Map<Name, Integer> betLog = betHistory.getBetHistory();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(betLog.get(firstPlayer)).isEqualTo(10_000);
            softAssertions.assertThat(betLog.get(secondPlayer)).isEqualTo(20_000);
        });
    }

}
