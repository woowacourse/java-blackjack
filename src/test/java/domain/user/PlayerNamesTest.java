package domain.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("NonAsciiCharacters")
public class PlayerNamesTest {

    @Test
    void 생성시_빈값이_들어오면_예외발생_테스트() {
        List<String> names = Collections.emptyList();
        Assertions.assertThatThrownBy(() -> new PlayerNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("빈 값이 입력되었습니다.");
    }

    @Test
    void 생성시_공백으로_이루어진_이름이_포함되어_있을때_예외발생_테스트() {
        List<String> names = Arrays.asList("가", "         ", "  나", " ");
        Assertions.assertThatThrownBy(() -> new PlayerNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공백으로 이루어진 이름은 사용할 수 없습니다.");
    }

    @Test
    void 생성시_중복된_이름이_포함되어_있을때_예외발생_테스트() {
        List<String> names1 = Arrays.asList("포비", "제이슨", "포비2", "포비");
        Assertions.assertThatThrownBy(() -> new PlayerNames(names1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되는 이름이 존재합니다.");

        List<String> names2 = Arrays.asList("포비", "제이슨", "포비2", "   포비   ");
        Assertions.assertThatThrownBy(() -> new PlayerNames(names2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복되는 이름이 존재합니다.");
    }
}
