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

    @Nested
    @DisplayName("미션 3")
    class Mission3 {
        @Test
        void 숫자_타입의_SimpleList_를_받아_모든_값을_더해주는_메서드를_구현한다() {
            // given
            final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
            final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

            Assertions.assertThat(SimpleList.sum(doubleValues)).isEqualTo(1.2); // 1.2
            Assertions.assertThat(SimpleList.sum(intValues)).isEqualTo(3);  // 3
        }
    }

}
