package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NamesTest {

    @DisplayName("전체 플레이어의 수는 1명 이상 7명 이하여야 한다.")
    @Test
    void validateSizeTest() {
        assertDoesNotThrow(() -> new Names(List.of("깃짱", "망고")));
        assertThatThrownBy(() -> new Names(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Names(List.of("깃짱", "망고", "저문", "이리내", "디노", "오잉", "체인저", "토리")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
