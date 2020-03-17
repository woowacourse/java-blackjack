package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DecisionTest {
    @ParameterizedTest
    @CsvSource(value = {"y,true", "Y,true", "n,false", "N,false"})
    @DisplayName("Y,y,N,n 이 입력됐을 때 제대로 boolean 값으로 반환하는지 확인")
    void inputYN(String value, boolean expected) {
        assertThat(Decision.chosenBy(value)).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"k", "asdf", "안돼", "154"})
    @DisplayName("잘못된 값이 입력됐을 때 exception 확인")
    void wrongInput(String value) {
        assertThatThrownBy(() -> Decision.chosenBy(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
