package techcourse.jcf.mission;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GenericTest {
    @Test
    @DisplayName("미션 1 구현 확인 테스트")
    void mission_one() {
        SimpleList<Integer> values = new SimpleArrayList<>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);
        assertThat(first).isEqualTo(1);
        assertThat(second).isEqualTo(2);
    }

    @Test
    @DisplayName("미션 2 구현 확인 테스트")
    void mission_two() {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.fromArrayToList(arrays);

        assertThat(values.get(0)).isEqualTo("first");
        assertThat(values.get(1)).isEqualTo("second");
    }

    @Test
    @DisplayName("미션 3 구현 확인 테스트")
    void mission_three() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        assertThat(doubleTotal).isEqualTo(1.2);
        assertThat(intTotal).isEqualTo(3);
    }

    @Test
    @DisplayName("미션 4 구현 확인 테스트")
    void mission_four() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);


        assertThat(filteredDoubleValues.get(0)).isEqualTo(0.5);
        assertThat(filteredDoubleValues.get(1)).isEqualTo(0.7);
        assertThat(filteredIntValues.get(0)).isEqualTo(1);
        assertThat(filteredIntValues.get(1)).isEqualTo(2);
    }

    @Test
    @DisplayName("미션 5 구현 확인 테스트")
    void mission_five() {
        final var laserPrinter = new LaserPrinter();

        final SimpleList<Printer> printers = new SimpleArrayList<>();
        final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<>(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        assertThat(printers.get(0)).isEqualTo(laserPrinter); // true
    }
}
