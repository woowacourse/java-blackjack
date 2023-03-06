package techcourse.jcf.mission.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class Mission {

    @Test
    void 미션_01() {
        final SimpleList<Integer> intValues = new SimpleArrayList<>();
        intValues.add(1);
        intValues.add(2);
        final Integer first = intValues.get(0);
        final Integer second = intValues.get(1);

        assertAll(
                () -> assertThat(first).isEqualTo(1),
                () -> assertThat(second).isEqualTo(2)
        );
    }

    @Test
    void 미션_02() {
        final String[] values = {"first", "second"};
        final SimpleList<String> convertedValues = SimpleList.fromArrayToList(values);

        final String first = convertedValues.get(0);
        final String second = convertedValues.get(1);

        assertAll(
                () -> assertThat(first).isEqualTo("first"),
                () -> assertThat(second).isEqualTo("second")
        );
    }

    @Test
    void 미션_03() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        assertAll(
                () -> assertThat(doubleTotal).isEqualTo(1.2),
                () -> assertThat(intTotal).isEqualTo(3)
        );
    }

    @Test
    void 미션_04() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        assertAll(
                () -> assertThat(filteredDoubleValues.size()).isEqualTo(2),
                () -> assertThat(filteredIntValues.size()).isEqualTo(2)
        );
    }

    @Test
    void 미션_05() {
        class Printer {
        }

        class LaserPrinter extends Printer {
        }

        LaserPrinter laserPrinter = new LaserPrinter();

        SimpleList<Printer> printers = new SimpleArrayList<>();
        SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        assertThat(printers.get(0)).isEqualTo(laserPrinter);
    }

}
