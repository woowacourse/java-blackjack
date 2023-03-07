package techcourse.jcf.mission;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleArrayListGenericTest {


    @Test
    void testAddString() {
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList<>();

        simpleArrayList.add("폴로");

        assertThat(simpleArrayList.get(0)).isEqualTo("폴로");
    }

    @Test
    void testAddInteger() {
        SimpleArrayList<Integer> simpleArrayList = new SimpleArrayList<>();

        simpleArrayList.add(10);

        assertThat(simpleArrayList.get(0)).isEqualTo(10);
    }

    @Test
    void set() {
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList<>();
        simpleArrayList.add("폴로");
        simpleArrayList.add("마코");

        simpleArrayList.set(1, "mako");

        assertThat(simpleArrayList.get(1)).isEqualTo("mako");
    }

    @Test
    void indexOfString() {
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList<>();
        simpleArrayList.add("폴로");
        simpleArrayList.add("마코");

        assertThat(simpleArrayList.indexOf("폴로")).isEqualTo(0);
    }

    @Test
    void size() {
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList<>();
        simpleArrayList.add("폴로");
        simpleArrayList.add("마코");

        assertThat(simpleArrayList.size()).isEqualTo(2);
    }

    @Test
    void isEmpty() {
        SimpleArrayList<String> simpleArrayList = new SimpleArrayList<>();

        assertThat(simpleArrayList.isEmpty()).isTrue();
    }

    @Test
    void removeByValue() {
        SimpleArrayList<Integer> integerSimpleArrayList = new SimpleArrayList<>();
        integerSimpleArrayList.add(1);
        integerSimpleArrayList.add(3);
        integerSimpleArrayList.add(2);

        integerSimpleArrayList.remove(Integer.valueOf(3));

        assertThat(integerSimpleArrayList.contains(3)).isFalse();
    }

    @Test
    void removeByIndex() {
        SimpleArrayList<Integer> integerSimpleArrayList = new SimpleArrayList<>();
        integerSimpleArrayList.add(1);
        integerSimpleArrayList.add(3);
        integerSimpleArrayList.add(2);

        integerSimpleArrayList.remove(1);

        assertThat(integerSimpleArrayList.contains(3)).isFalse();
    }

    @Test
    void clear() {
        SimpleArrayList<Integer> integerSimpleArrayList = new SimpleArrayList<>();
        integerSimpleArrayList.add(1);
        integerSimpleArrayList.add(3);
        integerSimpleArrayList.add(2);

        integerSimpleArrayList.clear();

        assertThat(integerSimpleArrayList.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("String 배열을 받아 SimpleList<String>으로 변환한다.")
    void fromArrayToListString() {
        String[] strings = {"폴로", "로지"};

        SimpleArrayList<String> list = SimpleArrayList.fromArrayToList(strings);

        assertThat(list.get(0)).isEqualTo("폴로");
        assertThat(list.get(1)).isEqualTo("로지");
    }

    @Test
    @DisplayName("Integer 배열을 받아 SimpleList<Integer>로 변환한다.")
    void fromArrayToListInteger() {
        Integer[] integers = {1, 2, 3};

        SimpleArrayList<Integer> integerSimpleArrayList = SimpleArrayList.fromArrayToList(integers);

        assertThat(integerSimpleArrayList.get(0)).isEqualTo(1);
        assertThat(integerSimpleArrayList.get(1)).isEqualTo(2);
        assertThat(integerSimpleArrayList.get(2)).isEqualTo(3);
    }

    @Test
    @DisplayName("sum method를 통해 SimpleList<Integer>의 합계를 구한다.")
    void sum() {
        Integer[] integers = {1, 2, 3};
        SimpleArrayList<Integer> integerSimpleArrayList = SimpleArrayList.fromArrayToList(integers);

        double sum = SimpleArrayList.sum(integerSimpleArrayList);

        assertThat(sum).isEqualTo(6);
    }

    @Test
    @DisplayName("sum method를 통해 SimpleList<double>의 합계를 구한다.")
    void sumDouble() {
        Double[] integers = {0.5, 0.7};
        SimpleArrayList<Double> integerSimpleArrayList = SimpleArrayList.fromArrayToList(integers);

        double sum = SimpleArrayList.sum(integerSimpleArrayList);

        assertThat(sum).isEqualTo(1.2);
    }

    @Test
    void filterNegative() {
        Double[] integers = {0.5, -0.7, 0.7, -0.3};
        SimpleArrayList<Double> doubleSimpleArrayList = SimpleArrayList.fromArrayToList(integers);

        SimpleArrayList<Double> withoutNegative = SimpleArrayList.filterNegative(doubleSimpleArrayList);

        assertThat(withoutNegative.size()).isEqualTo(2);
        assertThat(withoutNegative.get(0)).isEqualTo(0.5);
        assertThat(withoutNegative.get(1)).isEqualTo(0.7);
    }

    @Test
    void filterNegativeInteger() {
        Integer[] integers = {1, 2, -3, -4, 5};
        SimpleArrayList<Integer> integerSimpleArrayList = SimpleArrayList.fromArrayToList(integers);

        SimpleArrayList<Integer> withoutNegative = SimpleArrayList.filterNegative(integerSimpleArrayList);

        assertThat(withoutNegative.size()).isEqualTo(3);
        assertThat(withoutNegative.get(0)).isEqualTo(1);
        assertThat(withoutNegative.get(1)).isEqualTo(2);
        assertThat(withoutNegative.get(2)).isEqualTo(5);
    }
}
