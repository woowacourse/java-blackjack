package mission;

import static mission.SimpleList.*;

import org.junit.jupiter.api.Test;

class GeneratorTest {
    @Test
    public void mission1() {
        SimpleList<Integer> values = new SimpleArrayList<>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);
    }

    @Test
    public void mission2() {
        final String[] arrays = {"first", "second"};
        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);

        System.out.println(values);
    }

    @Test
    public void mission3() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        final double doubleTotal = sum(doubleValues); // 1.2
        final double intTotal = sum(intValues);  // 3
    }

    @Test
    public void mission4() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = filterNegative(intValues);
    }

    @Test
    public void mission5() {
        class Printer {
        }
        class LaserPrinter extends Printer {
        }

        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        System.out.println(printers.get(0) == laserPrinter); // true
    }
}
