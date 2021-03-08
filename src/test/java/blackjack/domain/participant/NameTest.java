package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class NameTest {

    @Test
    @DisplayName("생성 테스트")
    void createName() {
        Name name = new Name("air");
        assertThat(name).isEqualTo(new Name("air"));
    }

    @Test
    @DisplayName("생성 실패 - 이름 글자수 검사")
    void checkNameLength() {
        assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
