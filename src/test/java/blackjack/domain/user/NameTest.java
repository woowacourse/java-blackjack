package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {

    private static final String NAME_LENGTH_ERROR = "[ERROR] 이름은 한글자 이상 입력해 주세요.";

    @Test
    @DisplayName("이름 길이 유효성 검사 확인")
    void nameLength() {
        assertThatThrownBy(() -> {
            new Name(" ");
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(NAME_LENGTH_ERROR);
    }

    @Test
    @DisplayName("이름 인스턴스 동일 확인")
    void name() {
        Name name = new Name("sorong");
        assertThat(name).isEqualTo(new Name("sorong"));
    }
}
