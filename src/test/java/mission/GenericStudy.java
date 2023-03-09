package mission;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GenericStudy {

    @Test
    public void genericMission1() {
        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        assertThat(first).isEqualTo(1);
        assertThat(second).isEqualTo(2);
    }

    @Test
    public void genericMission2() {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);
        assertThat(values.get(0)).isEqualTo("first");
        assertThat(values.get(1)).isEqualTo("second");
    }

    @Test
    public void genericMission3() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3
    }

    @Test
    public void genericMission4() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, -2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        assertThat(filteredDoubleValues.size()).isEqualTo(2);
        assertThat(filteredIntValues.size()).isEqualTo(1);
    }

    class Printer {
    }

    class LaserPrinter extends Printer {
    }

    @Test
    public void genericMission5() {
        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        assertThat(printers.get(0) == laserPrinter).isTrue();
    }

}
