package mini;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GenericStudy {
    @Test
    void 미션1() throws Exception {
        SimpleList<Integer> values = new SimpleArrayList<>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        assertThat(first).isEqualTo(1);
        assertThat(second).isEqualTo(2);
    }

    @Test
    void 미션2() throws Exception {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.fromArrayToList(arrays);

        assertThat(values.size()).isEqualTo(2);
        for (int i = 0; i < values.size(); i++) {
            assertThat(values.get(i).equals(arrays[i]));
        }
    }

    @Test
    void 미션3() throws Exception {
        SimpleArrayList<String> list = new SimpleArrayList<>();

        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        assertThat(doubleTotal).isEqualTo(1.2);
        assertThat(intTotal).isEqualTo(3);
    }

    @Test
    void 미션4() throws Exception {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        assertThat(filteredDoubleValues.size()).isEqualTo(2);
        assertThat(filteredIntValues.size()).isEqualTo(2);
    }

    @Test
    void 미션5() throws Exception {
        class Printer {
        }
        class LaserPrinter extends Printer {
        }

        //////

        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        System.out.println(printers.get(0) == laserPrinter); // true
        assertThat(printers.get(0)).isEqualTo(laserPrinter);
    }
}
