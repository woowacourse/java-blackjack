package generic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleArrayListTest {

    @Test
    @DisplayName("List에 Integer 값을 add한다.")
    void addIntegerTest() {
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>();

        arrayList.add(10);

        assertThat(arrayList.get(0))
                .isEqualTo(10);
    }

    @Test
    void addIntegerOverDefaultCapacity() {
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>(array);

        arrayList.add(11);

        assertThat(arrayList.getValues())
                .contains(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
    }


    @Test
    @DisplayName("List안에 원하는 index에 값을 넣고, 기존에 있는 값을 한칸 뒤로 민다.")
    void addValueAtIndexTest() {
        Integer[] array = {1, 2, 3, 4};
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>(array);

        arrayList.add(0, 0);

        assertThat(arrayList.getValues())
                .contains(0, 1, 2, 3, 4);
    }

    @Test
    void set() {
        Integer[] array = {0};
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>(array);

        final Integer setReturnValue = arrayList.set(0, 1);

        assertThat(setReturnValue).isEqualTo(0);
    }

    @Test
    void get() {
        final Integer[] array = {0, 1, 2};
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>(array);

        final Integer getValue = arrayList.get(0);

        assertThat(getValue)
                .isEqualTo(0);
    }

    @Test
    void contains() {
        final Integer[] array = {1};
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>(array);

        assertAll(
                () -> assertThat(arrayList.contains(1)).isTrue(),
                () -> assertThat(arrayList.contains(0)).isFalse()
        );
    }

    @Test
    void indexOf() {
        final Integer[] array = {0, 1};
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>(array);

        assertAll(
                () -> assertThat(arrayList.indexOf(1)).isEqualTo(1),
                () -> assertThat(arrayList.indexOf(2)).isEqualTo(-1)
        );
    }

    @Test
    void size() {
        final Integer[] array = {0, 1};
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>(array);

        assertThat(arrayList.size()).isEqualTo(2);
    }

    @Test
    void isEmpty() {
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>();

        assertThat(arrayList.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("값을 넣어 있다면 삭제 후 true를 반환하고, 없다면 false를 반환한다.")
    void removeValueAndReturnBoolean() {
        final Integer[] array = {0};
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>(array);

        final boolean isRemove = arrayList.remove(Integer.valueOf(0));

        assertAll(
                () -> assertThat(isRemove).isTrue(),
                () -> assertThat(arrayList)
                        .extracting("size")
                        .isEqualTo(0)
        );
    }

    @Test
    void removeValueAndReturn() {
        final Integer[] array = {0, 1};
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>(array);

        final Integer removedValue = arrayList.remove(0);

        assertAll(
                () -> assertThat(removedValue).isEqualTo(0),
                () -> assertThat(arrayList)
                        .extracting("size")
                        .isEqualTo(1)
        );
    }

    @Test
    @DisplayName("clear는 list안에 있는 값을 초기화 시킨다.")
    void clear() {
        final Integer[] array = {0, 2, 3, 4};
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>(array);

        arrayList.clear();

        assertAll(
                () -> assertThat(arrayList)
                        .extracting("size")
                        .isEqualTo(0),
                () -> assertThat(arrayList.contains(0)).isFalse()
        );
    }

    @Test
    @DisplayName("배열을 받아 SimpleList로 반환한다.")
    void fromArrayList() {
        final Integer[] arrays = {1, 2, 3, 4};
        final SimpleList<Integer> integerSimpleList = SimpleArrayList.<Integer>fromArrayToList(arrays);

        assertThat(integerSimpleList.get(0))
                .isEqualTo(1);
    }

    @Test
    @DisplayName("Number로 제한된 값을 합해서 반환한다.")
    void sumMethod() {
        final SimpleList<Double> doubleValues = new SimpleArrayList<>(0.5, 0.7);
        final SimpleList<Integer> intValues = new SimpleArrayList<>(1, 2);

        final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
        final double intTotal = SimpleList.sum(intValues);  // 3

        assertAll(
                () -> assertThat(doubleTotal).isEqualTo(1.2),
                () -> assertThat(intTotal).isEqualTo(3)
        );
    }
}
