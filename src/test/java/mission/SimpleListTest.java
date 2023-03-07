package mission;

import static mission.SimpleList.sum;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class SimpleListTest {

    @Test
    void mission2() {
        final String[] arrays = {"first", "second"};
        final SimpleList<String> result = SimpleList.<String>fromArrayToList(arrays);

        assertAll(
                () -> assertThat(result.contains("first")).isTrue(),
                () -> assertThat(result.contains("second")).isTrue()
        );
    }

    @Test
    void mission3() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        assertAll(
                () -> assertThat(sum(doubleValues)).isEqualTo(1.2),
                () -> assertThat(sum(intValues)).isEqualTo(3)
        );
    }

    @Test
    void mission4() {
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
    void mission5() {
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
