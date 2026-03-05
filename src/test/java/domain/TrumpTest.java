package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TrumpTest {

    @Test
    @DisplayName("트럼프 덱 생성 테스트")
    void 트럼프_덱_생성_테스트() {
        Trump trump = new Trump();
        int expected = 13 * 4;

        assertThat(trump).extracting("deck")
            .asInstanceOf(InstanceOfAssertFactories.LIST)
            .hasSize(expected);
    }
}
