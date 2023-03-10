package domain.participant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ParticipantMoneyTest {

    private ParticipantMoney participantMoney;

    @BeforeEach
    void init() {
        participantMoney = ParticipantMoney.create("10000");
    }

    @ParameterizedTest(name = "create()는 호출하면 ParticipantMoney를 생성한다.")
    @ValueSource(strings = {"1000", "100000000"})
    void create_whenCall_thenSuccess(final String money) {
        ParticipantMoney participantMoney = assertDoesNotThrow(() -> ParticipantMoney.create(money));
        assertThat(participantMoney)
                .isInstanceOf(ParticipantMoney.class);
    }

    @ParameterizedTest(name = "create()는 정수 값이 아닌 금액이 주어지면, 예외를 반환한다.")
    @ValueSource(strings = {"string", "!#@($", "", "12wow34"})
    void create_givenInvalidTypeMoney_thenFail(final String money) {
        assertThatThrownBy(() -> ParticipantMoney.create(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 정수 값이어야 합니다.");
    }

    @ParameterizedTest(name = "create()는 1000원 미만, 100,000,000원 초과를 베팅하면 예외를 발생시킨다")
    @ValueSource(strings = {"999", "100000001"})
    void create_givenInvalidBettingMoney_thenFail(final String bettingMoney) {
        assertThatThrownBy(() -> ParticipantMoney.create(bettingMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1,000원 이상, 100,000,000원 이하여야 합니다.");
    }

    @Test
    @DisplayName("zero()는 호출하면 0원에 대한 참가자의 돈이 반환된다.")
    void zero_whenCall_thenReturnZeroMoney() {
        // given
        final ParticipantMoney expected = ParticipantMoney.create(0);

        // when
        ParticipantMoney actual = ParticipantMoney.zero();

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("add()는 호출하면, 합친 베팅 금액이 반환된다.")
    void add_whenCall_thenReturnSumMoney() {
        // given
        final ParticipantMoney expected = ParticipantMoney.create(30000);

        // when
        ParticipantMoney actual = participantMoney.add(ParticipantMoney.create(20000));

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("subtract()는 호출하면, 뺀 베팅 금액이 반환된다.")
    void subtract_whenCall_thenReturnSubtractedMoney() {
        // given
        final ParticipantMoney expected = ParticipantMoney.create(5000);

        // when
        ParticipantMoney actual = participantMoney.subtract(ParticipantMoney.create(5000));

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("multiply()는 호출하면, 곱한 베팅 금액이 반환된다.")
    void multiply_whenCall_thenReturnSumMoney() {
        // given
        final ParticipantMoney expected = ParticipantMoney.create(15000);

        // when
        ParticipantMoney actual = participantMoney.multiply(1.5);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }
}
