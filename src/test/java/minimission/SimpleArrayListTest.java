package minimission;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleArrayListTest {

    @Test
    void mission1() {
        SimpleList<Integer> values = new SimpleArrayList<>();
        values.add(2);
        values.add(1);

        Integer first = values.get(0);
        Integer second = values.get(1);

        System.out.println(first);
        System.out.println(second);
    }

    @Test
    void mission2() {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);

        Assertions.assertThat(values.get(0)).isEqualTo("first");
        Assertions.assertThat(values.get(1)).isEqualTo("second");

    }

    @Test
    void mission3() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        System.out.println(doubleTotal);
        System.out.println(intTotal);
    }

    @Test
    void mission4() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        for (int i = 0; i < filteredDoubleValues.size(); i++) {
            System.out.println(filteredDoubleValues.get(i));
        }
        for (int i = 0; i < filteredIntValues.size(); i++) {
            System.out.println(filteredIntValues.get(i));
        }
    }

    @Test
    void mission5() {
        final var laserPrinter0 = new LaserPrinter();
        final var laserPrinter1 = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<>(laserPrinter0, laserPrinter1);

        SimpleList.copy(laserPrinters, printers);

        Assertions.assertThat(printers.get(0)).isEqualTo(laserPrinter0);
        Assertions.assertThat(printers.get(1)).isEqualTo(laserPrinter1);
    }

}
