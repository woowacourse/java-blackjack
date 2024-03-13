package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.player.Names;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NamesTest {
    @Test
    @DisplayName("이름 목록을 포함한 일급 컬렉션을 생성 한다.")
    public void Names_Instance_create_with_StringList() {
        List<String> values = List.of("초롱", "조이썬");

        Assertions.assertThatCode(() -> {
            var sut = Names.from(values);
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("중복된 이름이 포함되는 일급 컬렉션은 생성될 수 없다.")
    public void Names_Instance_not_include_duplicated_name() {
        List<String> values = List.of("초롱", "조이썬", "초롱");

        assertThatThrownBy(() -> Names.from(values))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름이 있습니다.");
    }

    @Test
    @DisplayName("최대 7명까지만 입력 가능하다.")
    public void Names_Instance_limit_size() {
        List<String> values = List.of("초롱","도비","조이썬","제우스","폰드","호티","배키","켬미");

        assertThatThrownBy(() -> Names.from(values))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("7명 까지만 가능합니다.");
    }
}
