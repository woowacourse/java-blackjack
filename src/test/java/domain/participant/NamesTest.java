package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class NamesTest {
    @Test
    void 이름이_없을_경우_예외가_발생한다() {
        List<String> rawNames = Collections.emptyList();

        assertThatThrownBy(() -> new Names(rawNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름 개수는 1 ~ 6개여야 합니다.");
    }

    @Test
    void 이름_개수가_최댓값을_넘을_경우_예외가_발생한다() {
        List<String> rawNames = List.of("뽀로로", "프린", "포비", "네오", "토미", "영이", "수달");

        assertThatThrownBy(() -> new Names(rawNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름 개수는 1 ~ 6개여야 합니다.");
    }

    @Test
    void 이름이_중복될_경우_예외가_발생한다() {
        String roro = "뽀로로";
        String prin = "프린";
        List<String> rawNames = List.of(roro, prin, prin);

        assertThatThrownBy(() -> new Names(rawNames))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된 이름이 존재합니다.");
    }

    @Test
    void 이름들을_정상적으로_생성한다() {
        List<String> rawNames = List.of("뽀로로", "프린", "포비", "네오", "토미", "영이");

        Names names = new Names(rawNames);

        assertThat(names.getNames()).hasSize(6);
    }
}
