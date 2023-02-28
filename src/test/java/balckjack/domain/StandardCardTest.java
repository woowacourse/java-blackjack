package balckjack.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StandardCardTest {

    @Test
    void createInstance() {
        StandardCard standardCard = new StandardCard(3);
        Assertions.assertThat(standardCard).isInstanceOf(StandardCard.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 11, 12})
    void valueIsOutOfBound(int input) {
        Assertions.assertThatThrownBy(() -> new StandardCard(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format("일반 카드의 번호는 2에서 10 사이여야 합니다. 입력값: %d", input));
    }
}