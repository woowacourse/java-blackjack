package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("이름 초기화 테스트")
    void nameInitTest() {
        final Name name = new Name("test");
        assertThat(name.getValue()).isEqualTo("test");
    }

    @Test
    @DisplayName("공백이 입력되면 예외 발생 테스트")
    void throwExceptionIfNameIsBlank() {
        assertThatThrownBy(() -> new Name(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Name.BLANK_NAME_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("이름과 똑같은 String이 들어오는지 확인")
    void isSameNameTest() {
        final Name name = new Name("test");

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(name.isSameName("test")).isTrue();
            softly.assertThat(name.isSameName("홍실")).isFalse();
        });
    }
}
