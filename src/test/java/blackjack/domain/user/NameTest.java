package blackjack.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NameTest {
    @DisplayName("같은 문자열을 가지는 Name 객체는 같은 것으로 간주한다.")
    @Test
    void equalsTest() {
        Name name = Name.of("kimkim");

        assertThat(name).isEqualTo(Name.of("kimkim"));
    }
}
