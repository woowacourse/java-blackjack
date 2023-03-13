package blackjack.domain;

import static blackjack.domain.ParticipantFixtures.DEALER_17;
import static blackjack.domain.ParticipantFixtures.PLAYER_16;
import static blackjack.domain.ParticipantFixtures.PLAYER_17;
import static blackjack.domain.ParticipantFixtures.PLAYER_20;
import static blackjack.domain.ParticipantFixtures.PLAYER_BLACKJACK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TotalProfitCalculatorTest {

    @DisplayName("딜러를 포함한 모든 참가자 별 최종 수익을 계산한다.")
    @Test
    void should_ReturnProfitByName_When_GivenDealerAndPlayers() {
        final TotalProfitCalculator totalProfitCalculator = new TotalProfitCalculator();

        final Map<String, BigDecimal> result = totalProfitCalculator.calculateProfitByParticipant(List.of(
                PLAYER_BLACKJACK, PLAYER_20, PLAYER_16, PLAYER_17), DEALER_17);

        assertThat(result).containsExactly(
                entry(DEALER_17.getName(), new BigDecimal("-1500.0")),
                entry(PLAYER_BLACKJACK.getName(), new BigDecimal("1500.0")),
                entry(PLAYER_20.getName(), new BigDecimal("1000.0")),
                entry(PLAYER_16.getName(), new BigDecimal("-1000.0")),
                entry(PLAYER_17.getName(), BigDecimal.ZERO));
    }
}
