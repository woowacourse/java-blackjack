package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SuitTest {

    @DisplayName("무늬 이름을 조회한다.")
    @Test
    void 이름_조회() {
        String suitName = Suit.CLOVER.getName();

        assertThat(suitName).isEqualTo("클로버");
    }

    @DisplayName("무늬의 갯수를 확인한다.")
    @Test
    void 무늬_갯수_확인() {
        int size = Suit.values().length;

        assertThat(size).isEqualTo(4);
    }
}
