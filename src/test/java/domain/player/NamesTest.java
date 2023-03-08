package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class NamesTest {

    @Test
    @DisplayName("중복된 이름이 입력되면, 예외가 발생한다")
    void givenDuplicateName_thenFail() {
        assertThatThrownBy(() -> Names.from(List.of("준팍", "준팍", "파워", "파워")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되지 않은 이름만 입력해주세요");
    }
}
