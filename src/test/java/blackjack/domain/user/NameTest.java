package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class NameTest {
    @DisplayName("Name 객체를 생성한다.")
    @Test
    void name() {
        Name name = new Name("amazzi");

        assertThat(name).isInstanceOf(Name.class);
    }

    @DisplayName("이름이 유효하지 않은 문자일 경우 예외를 발생한다.")
    @Test
    void validateCharacterException() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Name("ㄴㄴㄴ");
        }).withMessage("유효하지 읺은 이름입니다.");
    }

    @DisplayName("이름이 공백인 경우 예외를 발생한다.")
    @Test
    void validateEmptyException() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Name("");
        }).withMessage("유효하지 읺은 이름입니다.");
    }
}
