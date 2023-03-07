package mission;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleArrayListTest {

    @Test
    void mission1(){
        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        Assertions.assertThat(first).isEqualTo(1);
        Assertions.assertThat(second).isEqualTo(2);
    }

    @Test
    void mission2(){
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);

        Assertions.assertThat(values.get(0)).isEqualTo("first");
        Assertions.assertThat(values.get(1)).isEqualTo("second");
    }

    @Test
    void mission3(){
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        Assertions.assertThat(doubleTotal).isEqualTo(1.2);
        Assertions.assertThat(intTotal).isEqualTo(3);
    }

    @Test
    void mission4(){
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        Assertions.assertThat(filteredDoubleValues.get(0)).isEqualTo(0.5);
        Assertions.assertThat(filteredDoubleValues.get(1)).isEqualTo(0.7);
        Assertions.assertThat(filteredIntValues.get(0)).isEqualTo(1);
        Assertions.assertThat(filteredIntValues.get(1)).isEqualTo(2);
    }



    @Test
    void mission5(){
        class Printer { }
        class LaserPrinter extends Printer { }

        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        Assertions.assertThat(printers.get(0)).isEqualTo(laserPrinter);
    }

}
