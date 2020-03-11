package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NameTest {
    @Test
    @DisplayName("Name 생성")
    void create() {
        assertThat(new Name("name")).isInstanceOf(Name.class);
    }

    @Test
    @DisplayName("이름이 공백인 경우")
    void create_name_is_blank() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Name(""));
    }

    @Test
    @DisplayName("이름 확인")
    void getValue() {
        assertThat(new Name("name").getValue()).isEqualTo("name");
    }
}
