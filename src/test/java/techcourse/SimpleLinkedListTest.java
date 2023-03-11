package techcourse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SimpleLinkedListTest {

    private final SimpleList<String> emptyList = new SimpleLinkedList<>();
    private final SimpleList<String> size1List = new SimpleLinkedList<>();
    private final SimpleList<String> list = new SimpleLinkedList<>();
    private final List<SimpleList<String>> lists = List.of(emptyList, size1List, list);

    @BeforeEach
    void init() {
        size1List.add("0");
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
    }

    @Test
    void add_시_값을_추가한다() {
        // given
        String value = "add";

        lists.forEach(it -> {
            // when
            boolean result = it.add(value);

            // then
            assertAll(
                    () -> assertThat(result).isTrue(),
                    () -> assertThat(it.contains(value)).isTrue(),
                    () -> assertThat(it.indexOf(value)).isEqualTo(it.size() - 1),
                    () -> assertThat(it.get(it.indexOf(value))).isEqualTo(value)
            );
        });
    }

    @Test
    void add_시_추가할_위치를_정해주면_이후_존재하는_값들은_뒤로_밀린다() {
        // when
        emptyList.add(0, "1");
        emptyList.add(0, "0");
        emptyList.add(2, "2");
        list.add(2, "added 2");
        list.add(6, "added 6");

        // then
        assertAll(
                () -> assertThat(emptyList.get(0)).isEqualTo("0"),
                () -> assertThat(emptyList.get(1)).isEqualTo("1"),
                () -> assertThat(list.get(0)).isEqualTo("0"),
                () -> assertThat(list.get(1)).isEqualTo("1"),
                () -> assertThat(list.get(2)).isEqualTo("added 2"),
                () -> assertThat(list.get(3)).isEqualTo("2"),
                () -> assertThat(list.get(4)).isEqualTo("3"),
                () -> assertThat(list.get(5)).isEqualTo("4"),
                () -> assertThat(list.get(6)).isEqualTo("added 6"),
                () -> assertThat(list.get(7)).isEqualTo("5")
        );
    }

    @Test
    void size_시_현재_들어있는_값의_개수를_구한다() {
        assertAll(
                () -> assertThat(emptyList.size()).isEqualTo(0),
                () -> assertThat(list.size()).isEqualTo(6),
                () -> assertThat(size1List.size()).isEqualTo(1)
        );
    }

    @Test
    void set_시_값을_세팅한다() {
        // given
        int beforeListSize = list.size();

        // when
        size1List.set(0, "set 0");
        list.set(0, "set 0");
        list.set(3, "set 3");
        list.set(5, "set 5");

        // then
        assertAll(
                () -> assertThat(size1List.get(0)).isEqualTo("set 0"),
                () -> assertThat(list.get(0)).isEqualTo("set 0"),
                () -> assertThat(list.get(3)).isEqualTo("set 3"),
                () -> assertThat(list.get(5)).isEqualTo("set 5"),
                () -> assertThat(list.size()).isEqualTo(beforeListSize),
                () -> assertThat(size1List.size()).isEqualTo(1)
        );
    }

    @Test
    void contains() {
        // when & then
        assertAll(
                () -> assertThat(emptyList.contains("0")).isFalse(),
                () -> assertThat(size1List.contains("0")).isTrue(),
                () -> assertThat(size1List.contains("1")).isFalse(),
                () -> assertThat(list.contains("0")).isTrue(),
                () -> assertThat(list.contains("1")).isTrue(),
                () -> assertThat(list.contains("2")).isTrue(),
                () -> assertThat(list.contains("3")).isTrue(),
                () -> assertThat(list.contains("4")).isTrue(),
                () -> assertThat(list.contains("5")).isTrue(),
                () -> assertThat(list.contains("6")).isFalse()
        );
    }

    @Test
    void isEmpty() {
        assertAll(
                () -> assertThat(emptyList.isEmpty()).isTrue(),
                () -> assertThat(size1List.isEmpty()).isFalse(),
                () -> assertThat(list.isEmpty()).isFalse()
        );

        size1List.remove(0);
        list.remove(0);
        list.remove(0);
        assertAll(
                () -> assertThat(size1List.isEmpty()).isTrue(),
                () -> assertThat(list.isEmpty()).isFalse()
        );
    }

    @Test
    void remove1() {
        assertThat(size1List.remove(0)).isEqualTo("0");
        assertThat(size1List.isEmpty()).isTrue();
        size1List.add("0");
        size1List.add("1");
        assertThat(size1List.get(0)).isEqualTo("0");
        assertThat(size1List.get(1)).isEqualTo("1");
        size1List.add(2, "2");
        assertThat(size1List.get(2)).isEqualTo("2");

        assertThat(list.remove(list.size() - 1)).isEqualTo("5");
        list.add("5");
        list.add("6");
        assertThat(list.get(list.size() - 1)).isEqualTo("6");
        assertThat(list.get(list.size() - 2)).isEqualTo("5");
    }

    @Test
    void remove2() {
        assertThat(size1List.remove("0")).isTrue();
        assertThat(size1List.isEmpty()).isTrue();
        size1List.add("0");
        size1List.add("1");
        assertThat(size1List.get(0)).isEqualTo("0");
        assertThat(size1List.get(1)).isEqualTo("1");
        size1List.add(2, "2");
        assertThat(size1List.get(2)).isEqualTo("2");

        assertThat(list.remove("5")).isTrue();
        assertThat(list.contains("5")).isFalse();
        list.add("5");
        list.add("6");
        assertThat(list.get(list.size() - 1)).isEqualTo("6");
        assertThat(list.get(list.size() - 2)).isEqualTo("5");
    }

    @Test
    void clear() {
        // given
        list.add("123");

        // when
        list.clear();

        // then
        assertThat(list.isEmpty()).isTrue();
    }

    @Test
    void indexOut() {
        // when & then
        assertAll(
                () -> assertThatThrownBy(() -> emptyList.add(1, "12"))
                        .isInstanceOf(IndexOutOfBoundsException.class),
                () -> assertThatThrownBy(() -> emptyList.get(0))
                        .isInstanceOf(IndexOutOfBoundsException.class),
                () -> assertThatThrownBy(() -> emptyList.set(0, "a"))
                        .isInstanceOf(IndexOutOfBoundsException.class),
                () -> assertThatThrownBy(() -> emptyList.remove(0))
                        .isInstanceOf(IndexOutOfBoundsException.class)
        );
    }
}
