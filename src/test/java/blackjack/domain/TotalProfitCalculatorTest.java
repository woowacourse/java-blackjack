package blackjack.domain;

import static blackjack.domain.Fixtures.BLACKJACK_WIN_PLAYER;
import static blackjack.domain.Fixtures.DEALER_17;
import static blackjack.domain.Fixtures.LOSE_PLAYER;
import static blackjack.domain.Fixtures.PUSH_PLAYER;
import static blackjack.domain.Fixtures.WIN_PLAYER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

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

        final Map<String, Integer> result = totalProfitCalculator.calculateProfitByParticipant(DEALER_17, List.of(
                BLACKJACK_WIN_PLAYER, WIN_PLAYER, LOSE_PLAYER, PUSH_PLAYER
        ));

        assertThat(result).containsExactly(
                entry(DEALER_17.getName(), -1500), entry(BLACKJACK_WIN_PLAYER.getName(), 1500),
                entry(WIN_PLAYER.getName(), 1000), entry(LOSE_PLAYER.getName(), -1000), entry(PUSH_PLAYER.getName(), 0)
        );
    }
}
