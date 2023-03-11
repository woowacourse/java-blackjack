package domain.participant;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NamesTest {

    @DisplayName("Names를 정상적으로 생성한다.")
    @Test
    void create_success() {
        // given
        List<String> expected = List.of("pobi", "neo", "ori", "jay");
        // when && then
        assertThatNoException()
                .isThrownBy(() -> Names.from(expected));
    }

    @DisplayName("이름이가 4개를 초과하면 예외를 반환한다.")
    @Test
    void create_fail_by_size_over() {
        // given
        List<String> expected = List.of("pobi", "neo", "ori", "jay", "odo");
        // when && then
        assertThatThrownBy(() -> Names.from(expected))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름을 최소 1명, 최대 4명 입력해주세요.");
    }

    @DisplayName("플레이어의 수가 1명 미만이면 예외를 반환한다.")
    @Test
    void create_fail_by_size_under() {
        // given
        List<String> expected = List.of();
        // when && then
        assertThatThrownBy(() -> Names.from(expected))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름을 최소 1명, 최대 4명 입력해주세요.");
    }

    @DisplayName("이름이 중복되면 예외를 반환한다.")
    @Test
    void create_fail_by_duplicated() {
        //given
        List<String> expected = List.of("pobi", "pobi");
        //when && then
        assertThatThrownBy(() -> Names.from(expected))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 중복될 수 없습니다.");
    }
}
