package blackjack.domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("이름들")
public class NamesTest {
    @Test
    @DisplayName("여러명일 수 있다.")
    void playersCreateTest() {
        // given & when
        Names names = new Names(List.of("pobi", "lemone", "seyang"));

        // then
        assertThat(names.names())
                .usingRecursiveComparison()
                .isEqualTo(List.of(new Name("pobi"), new Name("lemone"), new Name("seyang")));
    }

    @Test
    @DisplayName("이름이 중복될 경우 예외가 발생한다.")
    void validateDuplicate() {
        // given
        List<String> names = List.of("pobi", "pobi");
        // when & then
        assertThatCode(() -> new Names(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 중복될 수 없습니다.");
    }
}
