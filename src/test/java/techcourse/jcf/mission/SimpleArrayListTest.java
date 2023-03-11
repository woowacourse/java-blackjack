package techcourse.jcf.mission;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleArrayListTest {

    @Test
    @DisplayName("미션1")
    void mission1() {
        //given
        SimpleList<Integer> values = new SimpleArrayList<>();

        //when
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        //then
        assertThat(first).isEqualTo(1);
        assertThat(second).isEqualTo(2);
    }

    @Test
    @DisplayName("미션2")
    void mission2() {
        //given
        final String[] arrays = {"first", "second"};


        //when
        final SimpleList<String> values = SimpleArrayList.<String>fromArrayToList(arrays);

        //then
        assertThat(values.get(0)).isEqualTo(arrays[0]);
        assertThat(values.get(1)).isEqualTo(arrays[1]);
    }

    @Test
    @DisplayName("미션3")
    void mission3() {
        //given
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        //when
        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        //then
        assertThat(doubleTotal).isEqualTo(1.2);
        assertThat(intTotal).isEqualTo(3);
    }

    @Test
    @DisplayName("미션4")
    void mission4() {
        //given
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2, -1, 3);

        //when
        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        //then
        assertThat(filteredDoubleValues.size()).isEqualTo(2);
        assertThat(filteredIntValues.size()).isEqualTo(3);
    }

    class Printer {
    }

    class LaserPrinter extends Printer {
    }

    @Test
    @DisplayName("미션5")
    void mission5() {
        //given
        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

        //when
        SimpleList.copy(laserPrinters, printers);

        //then
        assertThat(printers.get(0) == laserPrinter).isTrue();
    }

    @Test
    @DisplayName("미션5")
    void mission6() {
        //given
        final var laserPrinter = new LaserPrinter();
        final var Printer1 = new Printer();
        final var Printer2 = new Printer();


        final SimpleList<Printer> printers = new SimpleArrayList<Printer>(Printer1, Printer2);
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

        //when
        SimpleList.copy(laserPrinters, printers);

        //then
        assertThat(printers.get(0) == laserPrinter).isTrue();
        assertThat(printers.size()).isEqualTo(1);
    }
}
