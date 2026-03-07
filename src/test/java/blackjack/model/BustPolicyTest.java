package blackjack.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BustPolicyTest {

    final BustPolicyImpl bustPolicy = new BustPolicyImpl();

    @ParameterizedTest
    @ValueSource(ints = {22, 30, 50, Integer.MAX_VALUE})
    void 점수가_임계치보다_높다면_버스트로_판단한다(int higherScore) {
        boolean bust = bustPolicy.isBust(higherScore);

        assertThat(bust).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 21})
    void 점수가_임계치보다_높지_않다면_버스트로_판단하지_않는다(int higherScore) {
        boolean bust = bustPolicy.isBust(higherScore);

        assertThat(bust).isFalse();
    }
}
