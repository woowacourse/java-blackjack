package list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class ListStudy {

    @Nested
    public class ArrayList {

        private SimpleArrayList<String> stringValues;
        private SimpleArrayList<Integer> integerValues;

        @BeforeEach
        void setup() {
            stringValues = new SimpleArrayList<>();
            integerValues = new SimpleArrayList<>();
            stringValues.add("init");
            integerValues.add(0);
        }

        @Test
        void add_test() {
            assertThat(stringValues.add("ditoo")).isTrue();
            assertThat(integerValues.add(1)).isTrue();
        }

        @Test
        void add_test2() {
            assertThatCode(() -> stringValues.add(0, "zero")).doesNotThrowAnyException();
            assertThatCode(() -> stringValues.add(100, "hundred")).isInstanceOf(RuntimeException.class);
            assertThatCode(() -> integerValues.add(0, 1)).doesNotThrowAnyException();
            assertThatCode(() -> integerValues.add(100, 100)).isInstanceOf(RuntimeException.class);
        }

        @Test
        void set_test() {
            assertThat(stringValues.set(0, "0")).isEqualTo("0");
            assertThatCode(() -> stringValues.set(100, "100")).isInstanceOf(RuntimeException.class);
            assertThat(integerValues.set(0, 0)).isEqualTo(0);
            assertThatCode(() -> integerValues.set(100, 100)).isInstanceOf(RuntimeException.class);
        }

        @Test
        void get_test() {
            assertThat(stringValues.get(0)).isEqualTo("init");
            assertThatCode(() -> stringValues.get(100)).isInstanceOf(RuntimeException.class);
            assertThat(integerValues.get(0)).isEqualTo(0);
            assertThatCode(() -> integerValues.get(100)).isInstanceOf(RuntimeException.class);
        }

        @Test
        void contains_test() {
            assertThat(stringValues.contains("init")).isTrue();
            assertThat(stringValues.contains("fifth")).isFalse();
            assertThat(integerValues.contains(0)).isTrue();
            assertThat(integerValues.contains(100)).isFalse();
        }

        @Test
        void indexOf_test() {
            assertThat(stringValues.indexOf("init")).isEqualTo(0);
            assertThat(stringValues.indexOf("fifth")).isEqualTo(-1);
            assertThat(integerValues.indexOf(0)).isEqualTo(0);
            assertThat(integerValues.indexOf(100)).isEqualTo(-1);
        }

        @Test
        void size_test() {
            assertThat(stringValues.size()).isEqualTo(1);
            assertThat(integerValues.size()).isEqualTo(1);
        }

        @Test
        void isEmpty_test() {
            assertThat(stringValues.isEmpty()).isFalse();
            assertThat(new SimpleArrayList<String>().isEmpty()).isTrue();
            assertThat(integerValues.isEmpty()).isFalse();
            assertThat(new SimpleArrayList<Integer>().isEmpty()).isTrue();
        }

        @Test
        void remove_by_value_test() {
            assertThat(stringValues.remove("init")).isTrue();
            assertThat(stringValues.isEmpty()).isTrue();
            assertThat(stringValues.remove("fifth")).isFalse();
            assertThat(integerValues.remove(Integer.valueOf(0))).isTrue();
            assertThat(integerValues.isEmpty()).isTrue();
            assertThat(integerValues.remove(Integer.valueOf(1))).isFalse();
        }

        @Test
        void remove_by_index_test() {
            assertThat(stringValues.remove(0)).isEqualTo("init");
            assertThat(stringValues.size()).isZero();
            assertThatCode(() -> stringValues.remove(100)).isInstanceOf(RuntimeException.class);
            assertThat(integerValues.remove(0)).isEqualTo(0);
            assertThat(integerValues.size()).isZero();
            assertThatCode(() -> integerValues.remove(100)).isInstanceOf(RuntimeException.class);
        }
//
//        // TODO values에 담긴 모든 값을 출력한다.
//        values.printAll();
//
//        assertThatCode(values::clear).doesNotThrowAnyException(); // 리스트를 초기화한다.
//        assertThat(values.isEmpty()).isTrue(); // 초기화되었는지 확인한다.
    }

    @Nested
    public class LinkedList {
        private SimpleLinkedList<String> stringValues;
        private SimpleLinkedList<Integer> integerValues;

        @BeforeEach
        void setup() {
            stringValues = new SimpleLinkedList<>();
            integerValues = new SimpleLinkedList<>();
            stringValues.add("init");
            integerValues.add(0);
        }

        @Test
        void add_test() {
            assertThat(stringValues.add("ditoo")).isTrue();
            assertThat(integerValues.add(1)).isTrue();
        }

        @Test
        void add_test2() {
            assertThatCode(() -> stringValues.add(0, "zero")).doesNotThrowAnyException();
            assertThatCode(() -> stringValues.add(100, "hundred")).isInstanceOf(RuntimeException.class);
            assertThatCode(() -> integerValues.add(0, 1)).doesNotThrowAnyException();
            assertThatCode(() -> integerValues.add(100, 100)).isInstanceOf(RuntimeException.class);
        }

        @Test
        void set_test() {
            assertThat(stringValues.set(0, "0")).isEqualTo("0");
            assertThatCode(() -> stringValues.set(100, "100")).isInstanceOf(RuntimeException.class);
            assertThat(integerValues.set(0, 0)).isEqualTo(0);
            assertThatCode(() -> integerValues.set(100, 100)).isInstanceOf(RuntimeException.class);
        }

        @Test
        void get_test() {
            assertThat(stringValues.get(0)).isEqualTo("init");
            assertThatCode(() -> stringValues.get(100)).isInstanceOf(RuntimeException.class);
            assertThat(integerValues.get(0)).isEqualTo(0);
            assertThatCode(() -> integerValues.get(100)).isInstanceOf(RuntimeException.class);
        }

        @Test
        void contains_test() {
            assertThat(stringValues.contains("init")).isTrue();
            assertThat(stringValues.contains("fifth")).isFalse();
            assertThat(integerValues.contains(0)).isTrue();
            assertThat(integerValues.contains(100)).isFalse();
        }

        @Test
        void indexOf_test() {
            assertThat(stringValues.indexOf("init")).isEqualTo(0);
            assertThat(stringValues.indexOf("fifth")).isEqualTo(-1);
            assertThat(integerValues.indexOf(0)).isEqualTo(0);
            assertThat(integerValues.indexOf(100)).isEqualTo(-1);
        }

        @Test
        void size_test() {
            assertThat(stringValues.size()).isEqualTo(1);
            assertThat(integerValues.size()).isEqualTo(1);
        }

        @Test
        void isEmpty_test() {
            assertThat(stringValues.isEmpty()).isFalse();
            assertThat(new SimpleArrayList<String>().isEmpty()).isTrue();
            assertThat(integerValues.isEmpty()).isFalse();
            assertThat(new SimpleArrayList<Integer>().isEmpty()).isTrue();
        }

        @Test
        void remove_by_value_test() {
            assertThat(stringValues.remove("init")).isTrue();
            assertThat(stringValues.isEmpty()).isTrue();
            assertThat(stringValues.remove("fifth")).isFalse();
            assertThat(integerValues.remove(Integer.valueOf(0))).isTrue();
            assertThat(integerValues.isEmpty()).isTrue();
            assertThat(integerValues.remove(Integer.valueOf(1))).isFalse();
        }

        @Test
        void remove_by_index_test() {
            assertThat(stringValues.remove(0)).isEqualTo("init");
            assertThat(stringValues.size()).isZero();
            assertThatCode(() -> stringValues.remove(100)).isInstanceOf(RuntimeException.class);
            assertThat(integerValues.remove(0)).isEqualTo(0);
            assertThat(integerValues.size()).isZero();
            assertThatCode(() -> integerValues.remove(100)).isInstanceOf(RuntimeException.class);
        }
    }
}
