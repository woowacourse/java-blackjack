package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class GenericStudy {

    @Test
    void 정수_타입_리스트를_생성한다() {
        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);

        assertAll(
                () -> assertThat(first).isEqualTo(1),
                () -> assertThat(second).isEqualTo(2)
        );
    }

    @Test
    void 배열을_받아서_리스트로_변환한다() {
        String[] arrays = {"first", "second"};

        SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);

        String first = values.get(0);
        String second = values.get(1);

        assertAll(
                () -> assertThat(first).isEqualTo("first"),
                () -> assertThat(second).isEqualTo("second")
        );
    }

    @Test
    void 숫자타입이라면_모든_값을_더한다() {
        Double[] doubleArrays = {0.5, 0.7};
        Integer[] intArrays = {1, 2};

        SimpleList<Double> doubleList = SimpleList.<Double>fromArrayToList(doubleArrays);
        SimpleList<Integer> intList = SimpleList.<Integer>fromArrayToList(intArrays);

        double doubleSum = SimpleList.sum(doubleList);
        double intSum = SimpleList.sum(intList);

        assertAll(
                () -> assertThat(doubleSum).isEqualTo(1.2),
                () -> assertThat(intSum).isEqualTo(3)
        );
    }

    @Test
    void 음수를_필터링한다() {
        Double[] doubleArrays = {-0.1, 0.5, 0.7};
        Integer[] intArrays = {-10, 1, 2};

        SimpleList<Double> doubleList = SimpleList.<Double>fromArrayToList(doubleArrays);
        SimpleList<Integer> intList = SimpleList.<Integer>fromArrayToList(intArrays);

        SimpleList<Double> filteredDoubleList = SimpleList.filterNegative(doubleList);
        SimpleList<Integer> filteredIntList = SimpleList.filterNegative(intList);

        assertAll(
                () -> assertThat(filteredDoubleList.size()).isEqualTo(2),
                () -> assertThat(filteredDoubleList.get(0)).isEqualTo(0.5),
                () -> assertThat(filteredDoubleList.get(1)).isEqualTo(0.7),
                () -> assertThat(filteredIntList.size()).isEqualTo(2),
                () -> assertThat(filteredIntList.get(0)).isEqualTo(1),
                () -> assertThat(filteredIntList.get(1)).isEqualTo(2)
        );
    }

    @Test
    void 리스트를_다른_리스트로_복사한다() {
        class Printer {

        }
        class LaserPrinter extends Printer {

        }

        LaserPrinter laserPrinter = new LaserPrinter();

        SimpleList<Printer> printers = new SimpleArrayList<>();
        SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<>();
        laserPrinters.add(laserPrinter);

        SimpleList.copy(laserPrinters, printers);

        assertThat(printers.get(0)).isEqualTo(laserPrinter);
    }
}
