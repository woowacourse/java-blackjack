package techcourse;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
            assertThat(values.get(0)).isEqualTo("first");
            assertThat(values.get(1)).isEqualTo("second");
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

            assertThat(SimpleList.sum(doubleValues)).isEqualTo(1.2); // 1.2
            assertThat(SimpleList.sum(intValues)).isEqualTo(3);  // 3
        }
    }

    @Nested
    @DisplayName("미션 4")
    class Mission4 {
        @Test
        void 숫자_타입의_SimpleList를_받아_음수를_제외하고_반환하는_메서드를_구현해본다() {
            // given
            final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
            final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

            // when
            final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
            final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

            // then
            assertThat(filteredDoubleValues.contains(-0.1)).isFalse();
            assertThat(filteredDoubleValues.contains(0.5)).isTrue();
            assertThat(filteredDoubleValues.contains(0.7)).isTrue();
            assertThat(filteredIntValues.contains(-10)).isFalse();
            assertThat(filteredIntValues.contains(1)).isTrue();
            assertThat(filteredIntValues.contains(2)).isTrue();
        }
    }

    @Nested
    @DisplayName("미션 5")
    class Mission5 {

        @Test
        void 리스트의_값을_다른_리스트로_복사하는_메서드를_구현해본다() {
            // given
            final var laserPrinter = new LaserPrinter();

            final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
            final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

            // when
            SimpleList.copy(laserPrinters, printers);

            // then
            Assertions.assertThat(printers.get(0)).isEqualTo(laserPrinter);
        }

        class Printer {
        }

        class LaserPrinter extends Printer {
        }
    }
}
