package blackjack.model.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NamesTest {

    @DisplayName("주어진 문자열을 바탕으로 플레이어 명단을 생성한다")
    @Test
    void createName() {
        Names names = new Names(List.of("초코", "치즈", "쿠키"));

        assertThat(names).isNotNull();
    }

    @DisplayName("예외: 플레이어 명단에는 중복된 이름이 없어야 한다")
    @Test
    void createName_Amount() {
        assertThatThrownBy(() -> new Names(List.of("치즈", "치즈", "쿠키")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 중복될 수 없습니다.");
    }
}
