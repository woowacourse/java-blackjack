package mission;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * mission 패키지는
 * 우아한 테크코스 미니 미션입니다.
 * 추가적으로 리뷰 하실 필요는 없습니다.
 */
public class ListStudy {
    @Test
    void mission1() {
        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        assertThat(first).isEqualTo(1);
        assertThat(second).isEqualTo(2);
    }

    @Test
    void mission2() {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);
        assertThat(values.contains("first")).isTrue();
        assertThat(values.contains("second")).isTrue();
    }

    @Test
    void mission3() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final int intTotal = SimpleList.sum(intValues);  // 3

        assertThat(doubleTotal).isEqualTo(1.2);
        assertThat(intTotal).isEqualTo(3);
    }

    @Test
    void mission4() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues); // 0.5, 0.7
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues); // 1,2

        assertThat(filteredDoubleValues.get(0)).isEqualTo(0.5);
        assertThat(filteredDoubleValues.get(1)).isEqualTo(0.7);

        assertThat(filteredIntValues.get(0)).isEqualTo(1);
        assertThat(filteredIntValues.get(1)).isEqualTo(2);
    }

    @Test
    void mission5() {
        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);
        SimpleList.copy(laserPrinters, printers);

        System.out.println(printers.get(0) == laserPrinter); // true

    }
}


class Printer { }
class LaserPrinter extends Printer { }
