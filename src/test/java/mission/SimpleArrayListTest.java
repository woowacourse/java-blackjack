package mission;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleArrayListTest {

    private SimpleArrayList<Integer> arrayList;

    @BeforeEach
    void setUp() {
        this.arrayList = new SimpleArrayList<>();
    }

    @Test
    @DisplayName("값 자체를 넣는 add() 테스트")
    void addTest_withOutIndex() {
        arrayList.add(1);

        assertThat(arrayList.get(0)).isEqualTo(1);
    }

    @Test
    @DisplayName("인덱스에 값을 넣는 add() 테스트")
    void addTest_withIndex() {
        arrayList.add(1);
        arrayList.add(2);

        arrayList.add(1, 3);
        assertAll(
                () -> assertThat(arrayList.get(0)).isEqualTo(1),
                () -> assertThat(arrayList.get(1)).isEqualTo(3),
                () -> assertThat(arrayList.get(2)).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("첫번째 인덱스에 값을 넣는 add() 테스트")
    void addTest_withIndex0() {
        arrayList.add(1);
        arrayList.add(2);

        arrayList.add(0, 3);
        assertAll(
                () -> assertThat(arrayList.get(0)).isEqualTo(3),
                () -> assertThat(arrayList.get(1)).isEqualTo(1),
                () -> assertThat(arrayList.get(2)).isEqualTo(2)
        );
    }

    @Test
    @DisplayName("마지막 인덱스에 값을 넣는 add() 테스트")
    void addTest_withLastIndex() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        arrayList.add(5, 6);

        assertAll(
                () -> assertThat(arrayList.get(0)).isEqualTo(1),
                () -> assertThat(arrayList.get(1)).isEqualTo(2),
                () -> assertThat(arrayList.get(2)).isEqualTo(3),
                () -> assertThat(arrayList.get(3)).isEqualTo(4),
                () -> assertThat(arrayList.get(4)).isEqualTo(5),
                () -> assertThat(arrayList.get(5)).isEqualTo(6)
        );
    }

    @Test
    void set() {
        arrayList.add(1);

        arrayList.set(0, 10);

        assertThat(arrayList.get(0)).isEqualTo(10);
    }

    @Test
    void contains() {
        arrayList.add(1);

        assertAll(
                () -> assertThat(arrayList.contains(1)).isTrue(),
                () -> assertThat(arrayList.contains(0)).isFalse()
        );
    }

    @Test
    void indexOf() {
        arrayList.add(1);

        assertAll(
                () -> assertThat(arrayList.indexOf(1)).isEqualTo(0),
                () -> assertThat(arrayList.indexOf(2)).isEqualTo(-1)
        );
    }

    @Test
    void size() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);

        assertThat(arrayList.size()).isEqualTo(6);
    }

    @Test
    void isEmpty_true() {
        assertThat(arrayList.isEmpty()).isTrue();
    }

    @Test
    void isEmpty_false() {
        arrayList.add(1);

        assertThat(arrayList.isEmpty()).isFalse();
    }

    @Test
    void remove_withValue() {
        arrayList.add(1);
        arrayList.add(2);

        arrayList.remove((Integer) 2);

        assertAll(
                () -> assertThat(arrayList.size()).isEqualTo(1),
                () -> assertThat(arrayList.contains(2)).isFalse()
        );
    }

    @Test
    void remove_withIndex() {
        arrayList.add(1);
        arrayList.add(2);

        arrayList.remove(1);

        assertAll(
                () -> assertThat(arrayList.size()).isEqualTo(1),
                () -> assertThat(arrayList.contains(2)).isFalse()
        );
    }

    @Test
    void validate_remove_outOfIndex() {
        arrayList.add(1);
        arrayList.add(2);

        assertThatThrownBy(() -> arrayList.remove(2))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("[ERROR] 인덱스가 범위를 벗어났습니다.");
    }

    @Test
    void clear() {
        arrayList.add(1);
        arrayList.add(2);

        arrayList.clear();

        assertThat(arrayList.size()).isEqualTo(0);
    }

    @Test
    void convertArrayToSimpleList() {
        String[] arrays = {"first", "second"};
        SimpleArrayList<String> stringSimpleArrayList = SimpleArrayList.fromArrayToList(arrays);

        assertAll(
                () -> assertThat(stringSimpleArrayList.get(0)).isEqualTo("first"),
                () -> assertThat(stringSimpleArrayList.get(1)).isEqualTo("second")
        );
    }
}
