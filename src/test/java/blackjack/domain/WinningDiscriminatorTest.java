package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.gambler.Name;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningDiscriminatorTest {
    @DisplayName("최종 승패의 결과를 계산한다.")
    @Test
    void aa() {
        // given
        Map<Name, Integer> playerScores = Map.of(new Name("라젤"), 19, new Name("레오"), 21, new Name("비타"), 30);
        WinningDiscriminator winningDiscriminator = new WinningDiscriminator(20, playerScores);

        // when
        Map<String, Integer> result = winningDiscriminator.judgeDealerResult();

        // then
        assertThat(result.get("승")).isEqualTo(2);
        assertThat(result.get("패")).isEqualTo(1);
        assertThat(result.get("무")).isEqualTo(0);

    }
}