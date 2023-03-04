package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SimpleArrayListTest {

    @Test
    public void simpleArrayList() {
        SimpleList<String> values = new SimpleArrayList<String>();

        values.add("first");
        values.add("second");
        assertThat(values.add("third")).isTrue(); // 세 번째 값을 추가한다.
        assertThat(values.size()).isEqualTo(3); // list의 크기를 구한다.
        assertThat(values.get(0)).isEqualTo("first"); // 첫 번째 값을 찾는다.
        assertThat(values.contains("first")).isTrue(); // "first" 값이 포함되어 있는지를 확인한다.
        assertThat(values.remove(0)).isEqualTo("first"); // 첫 번째 값을 삭제한다.
        assertThat(values.size()).isEqualTo(2); // 값이 삭제 됐는지 확인한다.
        assertThat(values.get(0)).isEqualTo("second");
        assertThat(values.get(1)).isEqualTo("third");
        assertThat(values.remove(1)).isEqualTo("third"); // 첫 번째 값을 삭제한다.
        assertThat(values.remove(0)).isEqualTo("second"); // 첫 번째 값을 삭제한다.
        assertThat(values.add("four")).isTrue();
        assertThat(values.get(0)).isEqualTo("four");
        values.add(0, "five");
        assertThat(values.get(0)).isEqualTo("five");
        assertThat(values.get(1)).isEqualTo("four");
        assertThat(values.contains("five")).isTrue();
        assertThat(values.isEmpty()).isFalse();
        assertThat(values.indexOf("five")).isEqualTo(0);
        assertThat(values.remove("five")).isTrue();
        assertThat(values.get(0)).isEqualTo("four");
        assertThat(values.set(0, "five")).isEqualTo("four");
        assertThat(values.get(0)).isEqualTo("five");
        values.clear();
        assertThat(values.size()).isEqualTo(0);
        assertThat(values.add("six")).isTrue();
        assertThat(values.add("seven")).isTrue();
        values.add(0, "eight");
        System.out.println(values);
    }

    @Test
    void mission2() {
        final String[] arrays = {"first", "second"};

        final SimpleList<String> values = SimpleList.fromArrayToList(arrays);

        assertThat(values.contains("first")).isTrue();
        assertThat(values.contains("second")).isTrue();
    }

    @Test
    void mission3() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        assertThat(doubleTotal).isEqualTo(1.2);
        assertThat(intTotal).isEqualTo(3);
    }

    @Test
    void mission4() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

        final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
        final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);

        assertThat(filteredDoubleValues.contains(-0.1)).isFalse();
        assertThat(filteredDoubleValues.contains(0.5)).isTrue();
        assertThat(filteredDoubleValues.contains(0.7)).isTrue();
        assertThat(filteredIntValues.contains(-10)).isFalse();
        assertThat(filteredIntValues.contains(1)).isTrue();
        assertThat(filteredIntValues.contains(2)).isTrue();
    }
}
