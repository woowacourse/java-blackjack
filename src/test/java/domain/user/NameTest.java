package domain.user;

import domain.user.Name;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NameTest {
    @Test
    void create() {
        assertThat(new Name("name")).isInstanceOf(Name.class);
    }

    @Test
    void create_이름이_공백인_경우() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Name(""));
    }
}


