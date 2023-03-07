package techcourse.icf.mission;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleArrayListTest {

    @Test
    @DisplayName("미션1")
    void putTypeInteger() {
        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        Assertions.assertThat(first).isEqualTo(1);
        Assertions.assertThat(second).isEqualTo(2);
    }

    @Test
    @DisplayName("미션2")
    void genericMethod() {
        final String[] arrays = {"first", "second"};
        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);
        final SimpleList<String> values2 = new SimpleArrayList<>();
        values2.add("first");
        values2.add("second");
        Assertions.assertThat(values.size()).isEqualTo(values2.size());
        for (int i = 0; i < arrays.length; i++) {
            Assertions.assertThat(values.get(i)).isEqualTo(values2.get(i));
        }
    }

    @Test
    @DisplayName("미션3")
    void boundedParameter() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        Assertions.assertThat(doubleTotal).isEqualTo(1.2);
        Assertions.assertThat(intTotal).isEqualTo(3);

    }

    @Test
    @DisplayName("미션4")
    void filter() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        for (int i = 0; i < filteredDoubleValues.size(); i++) {
            Assertions.assertThat(filteredDoubleValues.get(i) >= 0).isTrue();
        }
        for (int i = 0; i < filteredIntValues.size(); i++) {
            Assertions.assertThat(filteredIntValues.get(i) >= 0).isTrue();
        }
    }
    @Test
    @DisplayName("미션5")
    void copy(){
        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        Assertions.assertThat(printers.get(0) == laserPrinter).isTrue();
    }

}