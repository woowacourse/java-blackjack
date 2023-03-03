package techcourse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Missions {

    @Nested
    @DisplayName("미션 2")
    class Mission2 {
        @Test
        void 배열을_받아_SimpleList_로_변환한다() {
            // given
            final String[] arrays = {"first", "second"};

            // when
            final SimpleList<String> values = SimpleList.fromArrayToList(arrays);

            // then
            Assertions.assertThat(values.get(0)).isEqualTo("first");
            Assertions.assertThat(values.get(1)).isEqualTo("second");
        }
    }
}
