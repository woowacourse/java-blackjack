package blackjack.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("이름들")
class NamesInputTest {

    @DisplayName("중복이 되면, 예외가 발생한다.")
    @Test
    void duplicateNames() {
        //given
        List<String> names = List.of("koko", "choco", "choco");

        //when & then
        assertThatThrownBy(() -> NamesInput.from(names))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
