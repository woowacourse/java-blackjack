package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.game.GameResult;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerInfoTest {

    @Nested
    @DisplayName("create() 테스트")
    class CreateMethodTest {

        @ParameterizedTest(name = "파라미터로 입력된 name이 유효하면 PlayerInfo를 생성한다")
        @ValueSource(strings = {"1", "abcdeabcdeabcdeabcde"})
        void create_givenValidName_thenSuccess(final String validName) {
            final PlayerInfo playerInfo = assertDoesNotThrow(() -> PlayerInfo.create(validName));

            assertThat(playerInfo).isExactlyInstanceOf(PlayerInfo.class);
        }

        @ParameterizedTest(name = "파라미터로 입력된 이름이 null이면 예외가 발생한다")
        @NullSource
        void create_givenNull_thenFail(final String invalidName) {
            assertThatThrownBy(() -> PlayerInfo.create(invalidName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 Null일 수 없습니다.");
        }

        @ParameterizedTest(name = "파라미터로 입력된 이름이 비어 있으면 예외가 발생한다")
        @EmptySource
        void create_givenEmptyName_thenFail(final String invalidName) {
            assertThatThrownBy(() -> PlayerInfo.create(invalidName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 공백일 수 없습니다.");
        }

        @ParameterizedTest(name = "파라미터로 입력된 name의 길이가 1~20글자 사이가 아니라면 예외가 발생한다")
        @ValueSource(strings = {"abcdeabcdeabcdeabcdea"})
        void create_givenLongName_thenFail(final String invalidName) {
            assertThatThrownBy(() -> PlayerInfo.create(invalidName))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 1글자에서 20글자 사이여야 합니다.");
        }
    }

    @Nested
    @DisplayName("bet() 테스트")
    class BetMethodTest {

        private final PlayerInfo playerInfo = PlayerInfo.create("a");

        @Test
        @DisplayName("유효한 배팅 금액을 전달하면 PlayerBet을 초기화한다")
        void create_givenValidMoney_thenReturnPlayerBet() {
            assertThatCode(() -> playerInfo.bet(1000))
                    .doesNotThrowAnyException();
        }

        @Test
        @DisplayName("최소 금액을 배팅하지 않으면 예외가 발생한다")
        void create_givenLessThanMinimumMoney_thenFail() {
            Assertions.assertThatThrownBy(() -> playerInfo.bet(0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("최소 천 원 이상 배팅해주세요.");
        }

        @Test
        @DisplayName("정해진 금액 단위로 배팅하지 않으면 예외가 발생한다")
        void create_givenInvalidAmountUnit_thenFail() {
            Assertions.assertThatThrownBy(() -> playerInfo.bet(1100))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("천 원 단위로 배팅해주세요.");
        }

    }

    @Test
    @DisplayName("calculateBenefit()은 게임 결과를 건네주면 순이익을 반환한다")
    void calculateBenefit_givenGameResult_thenReturnBenefit() {
        // given
        final PlayerInfo playerInfo = PlayerInfo.create("a");
        final GameResult gameResult = GameResult.BLACKJACK_WIN;
        playerInfo.bet(1000);

        // when
        final BigDecimal actual = playerInfo.calculateBenefit(gameResult);

        // then
        final BigDecimal expected = new BigDecimal("500.0");

        assertThat(actual)
                .isEqualTo(expected);
    }
}
