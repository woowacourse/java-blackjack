package minimission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleArrayListTest {

    SimpleList simpleArrayList;

    @BeforeEach
    void init() {
        simpleArrayList = new SimpleArrayList();
    }

    @Test
    @DisplayName("add() : arrayList의 사이즈가 충분할 때, 값을 추가할 수 있다.")
    void test_add_enough_size() throws Exception {
        //given
        SimpleArrayList simpleArrayList = new SimpleArrayList();

        //when & then
        simpleArrayList.add("a");
        assertEquals(1, simpleArrayList.size());

        simpleArrayList.add("b");
        assertEquals(2, simpleArrayList.size());
    }

    @Test
    @DisplayName("add(value) : arrayList가 가득 찼을 때, 자동으로 현재 크기의 절반을 늘린다음에 값을 넣을 수 있다.")
    void test_add_full() throws Exception {
        //given
        SimpleArrayList simpleArrayList = new SimpleArrayList();

        //when & then
        for (int i = 0; i < 10; i++) {
            simpleArrayList.add("a");
        }

        simpleArrayList.add("b");
        assertEquals(11, simpleArrayList.size());
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("add(index, value) : index의 크기가 0보다 작거나, 현재 크기보다 클 경우에는 IndexOutOfBoundsException 발생한다.")
    void test_add_specific_index_indexOutOfBoundsException(int index) throws Exception {
        //given
        SimpleList simpleArrayList = new SimpleArrayList();

        //when & then
        assertThatThrownBy(() -> simpleArrayList.add(index, "a"))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    @DisplayName("add(index, value) : 원하는 index 에 값을 넣을 수 있다. 뒷 값들은 한 칸씩 밀려나간다.")
    void test_add_specific_index() throws Exception {
        // given
        for (int i = 0; i < 10; i++) {
            simpleArrayList.add(String.valueOf((char) (i + 'a')));
        }

        // when
        simpleArrayList.add(5, "k");


        // then
        assertEquals("k", simpleArrayList.get(5));
    }

    @Test
    @DisplayName("set() : 원하는 index에 값을 변경할 수 있다.")
    void test_set() throws Exception {
        //given
        for (int i = 0; i < 10; i++) {
            simpleArrayList.add(String.valueOf((char) (i + 'a')));
        }

        //when
        simpleArrayList.set(4, "k");

        //then
        assertEquals("k", simpleArrayList.get(4));
    }

    @Test
    @DisplayName("get() : 원하는 index 의 값을 가져올 수 있다.")
    void get_element_using_index() throws Exception {
        //when
        for (int i = 0; i < 10; i++) {
            simpleArrayList.add(String.valueOf((char) (i + 'a')));
        }

        //then
        assertAll(
                () -> assertEquals("a", simpleArrayList.get(0)),
                () -> assertEquals("b", simpleArrayList.get(1)),
                () -> assertEquals("c", simpleArrayList.get(2)),
                () -> assertEquals("d", simpleArrayList.get(3)),
                () -> assertEquals("e", simpleArrayList.get(4)),
                () -> assertEquals("f", simpleArrayList.get(5)),
                () -> assertEquals("g", simpleArrayList.get(6)),
                () -> assertEquals("h", simpleArrayList.get(7)),
                () -> assertEquals("i", simpleArrayList.get(8)),
                () -> assertEquals("j", simpleArrayList.get(9))
        );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "z -> false",
            "a -> true",
            "b -> true",
            "c -> true",
            "d -> true",
            "m -> false"
    }, delimiterString = " -> ")
    @DisplayName("contains() : 배열에 해당 값이 있다면 true를 반환한다")
    void test_contains(final String value, final boolean isContains) throws Exception {
        //given
        for (int i = 0; i < 10; i++) {
            simpleArrayList.add(String.valueOf((char) (i + 'a')));
        }

        //when & then
        assertEquals(isContains, simpleArrayList.contains(value));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "z -> -1",
            "a -> 0",
            "b -> 1",
            "c -> 2",
            "d -> 3"
    }, delimiterString = " -> ")
    @DisplayName("indexOf() : 배열에 해당 값이 없다면 -1 을 반환하고, 있으면 해당 index 를 반환한다")
    void test_indexOf_notIn(final String value, final int index) throws Exception {
        //given
        for (int i = 0; i < 10; i++) {
            simpleArrayList.add(String.valueOf((char) (i + 'a')));
        }

        //when & then
        assertEquals(index, simpleArrayList.indexOf(value));
    }

    @Test
    @DisplayName("size() : 현재 배열에 있는 값들의 개수를 구할 수 있다.")
    void test_size() throws Exception {
        //given
        assertEquals(0, simpleArrayList.size());

        for (int i = 0; i < 10; i++) {
            simpleArrayList.add(String.valueOf((char) (i + 'a')));
        }

        //when & then
        assertEquals(10, simpleArrayList.size());
    }

    @Test
    @DisplayName("empty() : 배열에 element 가 하나도 없으면 true를 반환한다.")
    void test_empty() throws Exception {
        //given
        assertTrue(simpleArrayList.isEmpty());

        for (int i = 0; i < 10; i++) {
            simpleArrayList.add(String.valueOf((char) (i + 'a')));
        }

        //when & then
        assertFalse(simpleArrayList.isEmpty());
    }

    @Test
    @DisplayName("remove(index) : 특정 index의 값을 삭제하고 뒤에 있는 값들을 당겨온다.")
    void test_remove_specific_index() throws Exception {
        //given
        for (int i = 0; i < 13; i++) {
            simpleArrayList.add(String.valueOf((char) (i + 'a')));
        }

        //when
        final String oldValue = simpleArrayList.remove(11);

        //then
        assertEquals("l", oldValue);
        assertEquals("m", simpleArrayList.get(11));
    }

    @Test
    @DisplayName("remove(value) : 특정 값을 삭제하고 뒤에 있는 값들을 당겨온다.")
    void test_remove_specific_value() throws Exception {
        //given
        for (int i = 0; i < 13; i++) {
            simpleArrayList.add(String.valueOf((char) (i + 'a')));
        }

        //when
        final boolean removeFlag = simpleArrayList.remove("l");

        //then
        assertTrue(removeFlag);
        assertEquals("m", simpleArrayList.get(11));
    }

    @Test
    @DisplayName("clear() : array의 값을 모두 비운다.")
    void test_clear() throws Exception {
        //given
        for (int i = 0; i < 13; i++) {
            simpleArrayList.add(String.valueOf((char) (i + 'a')));
        }

        //when
        simpleArrayList.clear();

        //then
        for (int i = 0; i < simpleArrayList.size(); i++) {
            assertNull(simpleArrayList.get(i));
        }
    }
}
