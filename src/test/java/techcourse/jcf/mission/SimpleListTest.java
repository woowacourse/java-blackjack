package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class SimpleListTest {

    @Test
    public void simpleArrayListTest() {
        simpleListTest(new SimpleArrayList<>());
        genericTest();
        pecsTest();
    }

    @Test
    public void simpleLinkedListTest() {
        simpleListTest(new SimpleLinkedList<>());
    }

    private void simpleListTest(SimpleList<String> values) {
        assertThat(values.add("first")).isTrue();
        assertThat(values.add("second")).isTrue();
        assertDoesNotThrow(() -> values.add(0, "zero"));

        assertThat(values.size()).isEqualTo(3);
        assertThat(values.get(0)).isEqualTo("zero");
        assertThat(values.contains("first")).isTrue();
        assertThat(values.remove(0)).isEqualTo("zero");

        assertThat(values.size()).isEqualTo(2);
        assertThat(values.get(0)).isEqualTo("first");
        assertThat(values.contains("second")).isTrue();
        assertThat(values.remove("first")).isTrue();

        assertThat(values.size()).isEqualTo(1);
        assertThat(values.get(0)).isEqualTo("second");
        assertThat(values.set(0, "first")).isEqualTo("second");
        assertThat(values.indexOf("first")).isEqualTo(0);

        assertThat(values.isEmpty()).isFalse();
        values.clear();
        assertThat(values.isEmpty()).isTrue();

        values.clear();
        assertDoesNotThrow(() -> {
            for (int i = 0; i < 30; i++) {
                values.add(String.valueOf(i));
            }
        });

        final String[] arrays = {"first", "second"};

        final SimpleList<String> arrayToList = SimpleList.<String>fromArrayToList(arrays);

        assertThat(arrayToList.get(0)).isEqualTo("first");
        assertThat(arrayToList.get(1)).isEqualTo("second");

        final SimpleList<Double> doubleValues = new SimpleArrayList<>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues);
        final double intTotal = SimpleList.sum(intValues);

        assertThat(doubleTotal).isEqualTo(1.2);
        assertThat(intTotal).isEqualTo(3);
    }

    private void genericTest() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        assertThat(filteredDoubleValues.get(0)).isEqualTo(0.5);
        assertThat(filteredDoubleValues.get(1)).isEqualTo(0.7);
        assertThat(filteredIntValues.get(0)).isEqualTo(1);
        assertThat(filteredIntValues.get(1)).isEqualTo(2);
    }

    class Printer { }
    class LaserPrinter extends Printer { }

    private void pecsTest() {
        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        System.out.println(printers.get(0) == laserPrinter); // true
    }
}
