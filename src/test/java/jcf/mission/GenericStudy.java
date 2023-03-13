package jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/08
 */
public class GenericStudy {

    @Test
    void test1() {
        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        assertThat(first).isEqualTo(1);
        assertThat(second).isEqualTo(2);
    }

    @Test
    void test2() {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.fromArrayToList(arrays);

        assertThat(values.get(0)).isEqualTo("first");
        assertThat(values.get(1)).isEqualTo("second");
    }

    @Test
    void test3() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3
    }

    @Test
    void test4() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);
    }

//    @Test
//    void test5() {
//        final var laserPrinter = new LaserPrinter();
//
//        final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
//        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);
//
//        SimpleList.copy(laserPrinters, printers);
//
//        System.out.println(printers.get(0) == laserPrinter); // true
//    }

    public class Printer {
    }

    public class LaserPrinter extends Printer {
    }
}
