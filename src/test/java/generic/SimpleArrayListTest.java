package generic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleArrayListTest {

    @Test
    @DisplayName("List에 Integer 값을 add한다.")
    void addStringTest() {
        final SimpleArrayList<Integer> arrayList = new SimpleArrayList<>();

        arrayList.add(10);

        assertThat(arrayList.getValues())
                .contains(10);
    }

    @Test
    void addStringOverDefaultCapacity() {
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

        Assertions.assertAll(
                () -> assertThat(arrayList.contains(1)).isTrue(),
                () -> assertThat(arrayList.contains(0)).isFalse()
        );
    }

    @Test
    void indexOf() {
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void remove() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void clear() {
    }
}
