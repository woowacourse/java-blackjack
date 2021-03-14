package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NamesTest {

    @Test
    @DisplayName("플레이어들의 이름 생성 테스트")
    void testCreatePlayerNames() {
        List<String> nameList = Arrays.asList("미립", "현구막", "포비");
        assertThat(new Names(nameList))
            .isInstanceOf(Names.class);
    }

    @Test
    @DisplayName("플레이어들의 이름 중복 테스트")
    void testDuplicateException() {
        List<String> nameList = Arrays.asList("미립", "현구막", "미립");
        assertThatThrownBy(() -> new Names(nameList)).isInstanceOf(IllegalArgumentException.class);
    }
}