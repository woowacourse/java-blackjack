package mission;

import static mission.SimpleList.sum;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class SimpleListTest {

    @Test
    void 배열을_받아_SimpleList로_변환한다() {
        final String[] arrays = {"first", "second"};
        final SimpleList<String> result = SimpleList.<String>fromArrayToList(arrays);

        assertAll(
                () -> assertThat(result.contains("first")).isTrue(),
                () -> assertThat(result.contains("second")).isTrue()
        );
    }

    @Test
    void 숫자_타입의_SimpleList를_받아_모든_값을_더한다() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        assertAll(
                () -> assertThat(sum(doubleValues)).isEqualTo(1.2),
                () -> assertThat(sum(intValues)).isEqualTo(3)
        );
    }

    @Test
    void 숫자_타입의_SimpleList를_받아_음수를_제외하고_반환한다() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        assertAll(
                () -> assertThat(filteredDoubleValues.size()).isEqualTo(2),
                () -> assertThat(filteredDoubleValues.get(0)).isEqualTo(0.5),
                () -> assertThat(filteredDoubleValues.get(1)).isEqualTo(0.7),
                () -> assertThat(filteredIntValues.size()).isEqualTo(2),
                () -> assertThat(filteredIntValues.get(0)).isEqualTo(1),
                () -> assertThat(filteredIntValues.get(1)).isEqualTo(2)
        );
    }

    @Test
    void 리스트의_값을_다른_리스트로_복사한다() {
        class Printer {
        }
        class LaserPrinter extends Printer {
        }

        final var laserPrinter = new LaserPrinter();
        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        assertThat(printers.get(0)).isEqualTo(laserPrinter);
    }
}
