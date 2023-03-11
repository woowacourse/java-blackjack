package domain.game;

import domain.participant.ParticipantMoney;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class EarningRateTest {

    @ParameterizedTest(name = "calculateMoney()는 호출하면, 조건에 맞게 베팅 금액을 계산한다")
    @CsvSource(value = {"BONUS:15000", "WIN:10000", "LOSE:-10000"}, delimiter = ':')
    void calculateMoney_givenParticipantMoney_thenReturnCalculatedMoney(final EarningRate earningRate, final double money) {
        // given
        final ParticipantMoney participantMoney = ParticipantMoney.create(10000);
        final ParticipantMoney expected = ParticipantMoney.create(money);

        // when
        final ParticipantMoney actual = earningRate.calculateMoney(participantMoney);

        // then
        assertThat(actual)
                .isEqualTo(expected);
    }
}
