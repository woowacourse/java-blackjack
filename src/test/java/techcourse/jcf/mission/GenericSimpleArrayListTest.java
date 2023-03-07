package techcourse.jcf.mission;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GenericSimpleArrayListTest {

    @DisplayName("Integer 값을 넣어도 리스트가 동작한다.")
    @Test
    void IntegerArrayListTest() {
        SimpleList<Integer> values = new SimpleArrayList<Integer>();
        values.add(1);
        values.add(2);

        Integer first = values.get(0);
        Integer second = values.get(1);
        assertThat(first).isEqualTo(1);
        assertThat(second).isEqualTo(2);
    }

    @DisplayName("배열을 받아 SimpleList로 변환하는 메서드를 구현한다.")
    @Test
    void fromArrayToListTest() {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);
    }

    @DisplayName("숫자 타입의 SimpleList를 받아 모든 값을 더해주는 메서드 구현")
    @Test
    void sumTest() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);
        final SimpleList<String> stringValues = new SimpleArrayList<>("s", "d");

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3
        assertThat(doubleTotal).isEqualTo(1.2);
        assertThat(intTotal).isEqualTo(3);
//        SimpleList.sum(stringValues); 컴파일 에러
    }

    @DisplayName("숫자 타입의 SimpleList를 받아 음수를 제외하고 반환하는 메서드 구현")
    @Test
    void filterNegativeTest() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

    }
}
