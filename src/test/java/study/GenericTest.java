package study;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GenericTest {
    @Test
    void mission1() {
        SimpleList<Integer> values1 = new SimpleArrayList<>();
        SimpleList<Integer> values2 = new SimpleLinkedList<>();
        values1.add(1);
        values2.add(1);
        values1.add(2);
        values2.add(2);

        Integer first1 = values1.get(0);
        Integer first2 = values2.get(0);
        Integer second1 = values1.get(1);
        Integer second2 = values2.get(1);

        assertThat(first1).isEqualTo(1);
        assertThat(first2).isEqualTo(1);
        assertThat(second1).isEqualTo(2);
        assertThat(second2).isEqualTo(2);
    }

    @Test
    void mission2() {
        final String[] arrays1 = {"first", "second"};
        final Integer[] arrays2 = {1, 2};

        final SimpleList<String> values1 = SimpleList.<String>fromArrayToArrayList(arrays1);
        final SimpleList<Integer> values2 = SimpleList.<Integer>fromArrayToLinkedList(arrays2);
        final SimpleList<String> values3 = SimpleList.<String>fromArrayToArrayList(arrays1);
        final SimpleList<Integer> values4 = SimpleList.<Integer>fromArrayToLinkedList(arrays2);

        assertAll(
            () -> assertThat(values1.get(0)).isEqualTo("first"),
            () -> assertThat(values2.get(0)).isEqualTo(1),
            () -> assertThat(values3.get(0)).isEqualTo("first"),
            () -> assertThat(values4.get(0)).isEqualTo(1)
        );
    }

    @Test
    void mission3() {
        final SimpleList<Double> doubleValues1 = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues1 = new SimpleArrayList<Integer>(1, 2);
        final SimpleList<Double> doubleValues2 = new SimpleLinkedList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues2 = new SimpleLinkedList<Integer>(1, 2);

        final double doubleTotal1 = SimpleList.sum(doubleValues1); // 1.2
        final double intTotal1 = SimpleList.sum(intValues1);  // 3
        final double doubleTotal2 = SimpleList.sum(doubleValues2); // 1.2
        final double intTotal2 = SimpleList.sum(intValues2);  // 3

        assertAll(
            () -> assertThat(doubleTotal1).isEqualTo(1.2),
            () -> assertThat(intTotal1).isEqualTo(3),
            () -> assertThat(doubleTotal2).isEqualTo(1.2),
            () -> assertThat(intTotal2).isEqualTo(3)
        );
    }

    @Test
    void mission4() {
        final SimpleArrayList<Double> doubleValues1 = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleArrayList<Integer> intValues1 = new SimpleArrayList<Integer>(-10, 1, 2);
        final SimpleLinkedList<Double> doubleValues2 = new SimpleLinkedList<Double>(-0.1, 0.5, 0.7);
        final SimpleLinkedList<Integer> intValues2 = new SimpleLinkedList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues1 = SimpleList.filterNegative(doubleValues1);
        final SimpleList<Integer> filteredIntValues1 = SimpleList.filterNegative(intValues1);
        final SimpleList<Double> filteredDoubleValues2 = SimpleList.filterNegative(doubleValues2);
        final SimpleList<Integer> filteredIntValues2 = SimpleList.filterNegative(intValues2);

        assertAll(
            () -> assertThat(filteredDoubleValues1.size()).isEqualTo(2),
            () -> assertThat(filteredIntValues1.size()).isEqualTo(2),
            () -> assertThat(filteredDoubleValues2.size()).isEqualTo(2),
            () -> assertThat(filteredIntValues2.size()).isEqualTo(2)
        );
    }

    @Test
    void mission5() {
        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers1 = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters1 = new SimpleArrayList<LaserPrinter>(laserPrinter);

        final SimpleList<Printer> printers2 = new SimpleLinkedList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters2 = new SimpleLinkedList<LaserPrinter>(laserPrinter);

        SimpleList.copy(laserPrinters1, printers1);
        SimpleList.copy(laserPrinters2, printers2);

        assertAll(
            () -> {
                for (int i = 0; i < printers1.size(); i++) {
                    assertThat(printers1.get(i)).isEqualTo(laserPrinter);
                }
            },
            () -> {
                for (int i = 0; i < printers2.size(); i++) {
                    assertThat(printers2.get(i)).isEqualTo(laserPrinter);
                }
            }
        );
    }

}

class Printer {
}

class LaserPrinter extends Printer {
}
