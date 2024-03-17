package blackjack.domain.player.info;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NamesTest {
    @Test
    @DisplayName("이름 목록을 포함한 일급 컬렉션을 생성 한다.")
    void create_with_StringList() {
        final List<String> values = List.of("초롱", "조이썬");

        Assertions.assertThatCode(() -> Names.from(values))
                  .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("중복된 이름이 포함되는 일급 컬렉션은 생성될 수 없다.")
    void not_include_duplicated_name() {
        final List<String> values = List.of("초롱", "조이썬", "초롱");

        assertThrows(IllegalArgumentException.class, () -> Names.from(values));
    }
}
