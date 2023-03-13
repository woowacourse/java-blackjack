package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SimpleArrayListTest {

    @Test
    void mission1() {
        final SimpleList<Integer> values = new SimpleArrayList<>();
        values.add(1);
        values.add(2);

        final Integer first = values.get(0);
        final Integer second = values.get(1);
        System.out.println(first);
        System.out.println(second);
    }

    @Test
    void mission2() {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.fromArrayToList(arrays);
        System.out.println(values);
    }

    @Test
    void mission3() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        assertThat(doubleTotal).isEqualTo(1.2);
        final double intTotal = SimpleList.sum(intValues);  // 3
        assertThat(intTotal).isEqualTo(3);
    }

    @Test
    void mission4() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);
        System.out.println(filteredDoubleValues);
        System.out.println(filteredIntValues);
    }

    @Test
    void mission5() {
        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        System.out.println(printers.get(0) == laserPrinter); // true
    }

    @Test
    void genericTest() {
        // 1. 인스턴스를 생성할 때 제네릭 클래스의 데이터 타입을 정한다.
        // 생성 시 new 연산에서 데이터 타입이 생략 가능하다. 변수 타입 선언에서 해당 타입을 알고 있기 때문이다.
        final SimpleList<Printer> printers = new SimpleArrayList<>();

        // 2. 메서드를 호출할 때 제네릭 메서드의 데이터 타입을 정한다.
        // 파라미터의 타입이 SimpleList<E>일 때, 메서드 호출 코드 작성 시 전달한 파라미터값에 따라 타입을 정한다.
    }
}
