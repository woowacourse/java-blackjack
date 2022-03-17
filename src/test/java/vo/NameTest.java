package vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class NameTest {
    @Test
    @DisplayName("Name 객체 생성 테스트")
    void createName() {
        // given
        String expected = "pobi";
        Name name = Name.from(expected);

        // when
        String actual = name.getName();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Name.from({0}) -> IAE")
    @NullAndEmptySource
    @DisplayName("Null또는 빈 문자열로 Name을 생성할 수 없다")
    void creatingNameWithNullOrEmptyStringShouldFail(String name) {
        assertThatThrownBy(() -> Name.from(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 빈 값일 수 없습니다");
    }
}
