import domain.participant.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameTest {

    @DisplayName("이름은 공백일 수 없다.")
    @Test
    void emptyName() {
        assertThatThrownBy(() -> new Name("")).isInstanceOf(IllegalArgumentException.class);
    }
}
