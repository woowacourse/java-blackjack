package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

public class PlayersTest {
    @Test
    @DisplayName("평범하고 정상적인 입력")
    void normalCase() {
        List<String> names = Arrays.asList("이종성", "김예림", "히히", "오렌지");
        List<Integer> betAmounts = Arrays.asList(10_000, 5_000, 50_000, 1_000_000);

        assertThat(new Players(names, betAmounts)).isInstanceOf(Players.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("입력된 이름 리스트의 null, empty 예외처리")
    void nullAndEmptyNames(List<String> names) {
        List<Integer> betAmounts = Arrays.asList(0);
        assertThatThrownBy(() -> new Players(names, betAmounts))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름이 비어있습니다.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("입력된 배팅금액 리스트의 null, empty 예외처리")
    void nullAndEmptyBetAmounts(List<Integer> betAmounts) {
        List<String> names = Arrays.asList("김예림", "이종성");
        assertThatThrownBy(() -> new Players(names, betAmounts))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("입력된 배팅금액이 없습니다.");
    }

    @Test
    @DisplayName("평범하고 정상적인 입력")
    void whenNamesSizeNotEqualToBetAmounts() {
        List<String> names = Arrays.asList("이종성", "김예림", "히히", "오렌지");
        List<Integer> betAmounts = Arrays.asList(10_000, 5_000, 50_000);

        assertThatThrownBy(() -> new Players(names, betAmounts))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름의 수와 배팅금액 수가 같아야 합니다.");
    }
}
