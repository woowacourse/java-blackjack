import domain.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameTest {

    @Test
    @DisplayName("이름은 공백일 수 없다.")
    void emptyName() {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Name("")).isInstanceOf(IllegalArgumentException.class);
    }
}
