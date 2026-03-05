package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BustPolicyTest {

    private final BustPolicy bustPolicy = new BustPolicy();

    @ParameterizedTest
    @DisplayName("점수가 임계치보다 높다면 버스트로 판단한다.")
    @ValueSource(ints = {22, 30, 50, Integer.MAX_VALUE})
    void judgeBustIfScoreHigher(int higherScore) {
        boolean bust = bustPolicy.isBust(higherScore);

        assertThat(bust).isTrue();
    }
}
