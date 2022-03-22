package blackjack.domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("이름을 생성한다.")
    public void testNameCreation() {
        // given
        String input = "pobi";
        // when
        Name name = new Name(input);
        // then
        Assertions.assertThat(name).isNotNull();
    }

    @Test
    @DisplayName("Null 인 경우 이름을 생성 할 수 없다.")
    public void throwsExceptionWithNull() {
        // given & when
        String input = null;

        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Name(input));
    }

    @Test
    @DisplayName("문자열이 비어있는 경우 이름을 생성할 수 없다.")
    public void throwsExceptionWithEmptyName() {
        // given & when
        String input = "";

        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new Name(input));
    }

    @Test
    @DisplayName("이름을 얻는다")
    public void testGetName() {
        // given
        Name name = new Name("pobi");
        // when
        String got = name.getName();
        // then
        Assertions.assertThat(got).isEqualTo("pobi");
    }
}