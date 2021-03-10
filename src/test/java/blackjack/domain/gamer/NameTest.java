package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {
    @Test
    @DisplayName("생성 - 대문자 포함")
    void create() {
        assertThatCode(()-> new Name("Better")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("생성 - 소문자로 공백없이")
    void create1() {
        assertThatCode(()-> new Name("better")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("생성 - 문자 앞이나 뒤 공백 포함")
    void create2() {
        assertThatCode(()-> new Name(" Better ")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("생성 - 한글 포함")
    void create3() {
        assertThatCode(()-> new Name("내이름은Better")).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("실패 - 이름 사이 공백 포함")
    void create4() {
        assertThatThrownBy(()-> new Name("내이름은 Better")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("생성 - 이름에 숫자 포함")
    void create5() {
        assertThatThrownBy(()-> new Name("Better2")).isInstanceOf(IllegalArgumentException.class);
    }

}
