package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NamesTest {

    private static final String NAME_DUPLICATE_ERROR = "[ERROR] 중복되는 이름을 입력할 수 없습니다.";

    @Test
    @DisplayName("이름 중복 유효성 검사 확인")
    void duplicate() {
        assertThatThrownBy(() -> {
            new Names("sorong, sorong");
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(NAME_DUPLICATE_ERROR);
    }
}
