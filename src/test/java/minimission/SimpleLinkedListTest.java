package minimission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleLinkedListTest {

    SimpleList simpleLinkedList;

    @BeforeEach
    void init() {
        simpleLinkedList = new SimpleLinkedList();
    }


    @Test
    @DisplayName("add(value) : 링크드리스트의 끝에 값을 넣을 수 있다.")
    void add_last() throws Exception {
        //when
        simpleLinkedList.add("a");
        simpleLinkedList.add("b");
        simpleLinkedList.add("c");

        //then
        assertAll(
                () -> assertEquals(3, simpleLinkedList.size()),
                () -> assertEquals("a", simpleLinkedList.get(0)),
                () -> assertEquals("b", simpleLinkedList.get(1)),
                () -> assertEquals("c", simpleLinkedList.get(2))
        );
    }

    @Test
    @DisplayName("add(index, value) : 링크드리스트의 원하는 곳에 값을 넣을 수 있다.")
    void add_specific_index() throws Exception {
        //given
        simpleLinkedList.add("a");
        simpleLinkedList.add("b");
        simpleLinkedList.add("c");

        //when a d b e c
        simpleLinkedList.add(1, "d");
        simpleLinkedList.add(3, "e");

        //then
        assertAll(
                () -> assertEquals(5, simpleLinkedList.size()),
                () -> assertEquals("a", simpleLinkedList.get(0)),
                () -> assertEquals("d", simpleLinkedList.get(1)),
                () -> assertEquals("b", simpleLinkedList.get(2)),
                () -> assertEquals("e", simpleLinkedList.get(3)),
                () -> assertEquals("c", simpleLinkedList.get(4))
        );
    }

    @Test
    @DisplayName("contains() : 특정 값이 포함되어있는지 알 수 있다.")
    void test_contains() throws Exception {
        //given
        simpleLinkedList.add("1");
        simpleLinkedList.add("2");
        simpleLinkedList.add("3");

        //when
        final boolean isContains = simpleLinkedList.contains("3");
        final boolean isNotContains = simpleLinkedList.contains("4");

        //then
        assertTrue(isContains);
        assertFalse(isNotContains);
    }

    @Test
    @DisplayName("indexOf() : 특정 값의 index를 알 수 있다.")
    void test_indexOf() throws Exception {
        //given
        simpleLinkedList.add("1");
        simpleLinkedList.add("2");
        simpleLinkedList.add("3");

        //when
        final int index = simpleLinkedList.indexOf("2");

        //then
        assertEquals(index, 1);
    }

    @Test
    @DisplayName("isEmpty() : 링크드리스트가 비어있는지 확인할 수 있다.")
    void test_isEmpty() throws Exception {
        //given
        final boolean empty = simpleLinkedList.isEmpty();

        assertTrue(empty);

        //when
        simpleLinkedList.add("1");
        simpleLinkedList.add("2");
        simpleLinkedList.add("3");

        final boolean notEmpty = simpleLinkedList.isEmpty();

        //then
        assertFalse(notEmpty);
    }

    @Test
    @DisplayName("remove(value) : 특정 값을 삭제할 수 있다.")
    void test_remove_value() throws Exception {
        //given
        simpleLinkedList.add("1");
        simpleLinkedList.add("2");
        simpleLinkedList.add("3");

        //when
        simpleLinkedList.remove("2");

        //then
        assertEquals(simpleLinkedList.size(), 2);
    }

    @Test
    @DisplayName("remove(index) : 특정 index의 값을 삭제할 수 있다.")
    void test_remove_index() throws Exception {
        //given
        simpleLinkedList.add("1");
        simpleLinkedList.add("2");
        simpleLinkedList.add("3");

        //when
        final String oldValue = simpleLinkedList.remove(2);

        //then
        assertEquals(oldValue, "3");
        assertEquals(simpleLinkedList.size(), 2);
    }

    @Test
    @DisplayName("clear() : LinkedList를 비울 수 있다.")
    void test_clear() throws Exception {
        //given
        simpleLinkedList.add("1");
        simpleLinkedList.add("2");
        simpleLinkedList.add("3");

        //when
        simpleLinkedList.clear();

        //then
        assertEquals(simpleLinkedList.size(), 0);
    }
}
