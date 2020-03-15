package blackjack.domain.result;

import blackjack.domain.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static blackjack.domain.card.Card.NULL_ERR_MSG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerResultTest {

    @DisplayName("예외 테스트: 생성자에 Null이 들어온 경우 Exception 발생")
    @Test
    void test1() {
        assertThatThrownBy(() -> new PlayerResult(null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new PlayerResult(new Name("쪼밀리"), null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);

        assertThatThrownBy(() -> new PlayerResult(null, ResultType.WIN))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining(NULL_ERR_MSG);
    }

    @DisplayName("PlayerResult가 주어진 ResultType을 가지고 있는지 확인")
    @ParameterizedTest
    @CsvSource(value = {"WIN, true", "DRAW, false", "LOSE, false"})
    void test2(ResultType type, boolean expected) {
        PlayerResult playerResult = new PlayerResult(new Name("쪼밀리"), ResultType.WIN);

        boolean actual = playerResult.hasSameResult(type);

        assertThat(actual).isEqualTo(expected);
    }
}
