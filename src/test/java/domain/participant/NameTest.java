package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("이름 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class NameTest {

    @Test
    void 이름이_빈값이면_예외가_발생한다() {
        String empty = " ";

        assertThatThrownBy(() -> new Name(empty))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 이름은 빈값을 입력 할 수 없습니다.");
    }
}
