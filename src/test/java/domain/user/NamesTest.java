package domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("입력된 이름 값들은 ")
public class NamesTest {
    @Test
    @DisplayName("하나일 수 있다.")
    void createSingleNameTest() {
        Names singleName = new Names(List.of("pobi"));

        assertThat(singleName.getNames()).isEqualTo(List.of(new Name("pobi")));
    }

    @Test
    @DisplayName("중복될 시 예외처리 된다.")
    void validateDuplicatedNamesTest() {
        assertThatThrownBy(() -> new Names(List.of("pobi", "neo", "pobi")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("5개가 초과될 시 예외처리 된다.")
    void validateNamesSizeTest() {
        assertThatThrownBy(() -> new Names(List.of("pobi", "neo", "hiiro", "mako", "ako", "split")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 플레이어는 최대 5명입니다.");
    }
}
