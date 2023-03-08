package techcourse.jcf.mission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("링크드 리스트는")
public class SimpleLinkedListTest {
    SimpleList<String> myLinkedList;

    @BeforeEach
    void initArray() {
        myLinkedList = new SimpleLinkedList<>();
    }


    @DisplayName("뒤에 값을 성공적으로 삽입했다면 true가 반환된다.")
    @Test
    void add_test1() {
        assertThat(myLinkedList.add("안녕")).isTrue();
    }

    @DisplayName("지정한 인덱스에 값을 성공적으로 삽입했다면 삽입한 값이 리스트 내에 포함되어있다.")
    @Test
    void add_test2() {
        myLinkedList.add(0, "안녕");
        assertThat(myLinkedList.contains("안녕")).isTrue();
    }

    @DisplayName("지정한 인덱스의 범위를 벗어난 위치에 값을 삽입하면 에러가 발생한다.")
    @Test
    void add_test3() {
        assertThatThrownBy(() -> myLinkedList.add(1, "안녕"))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("값을 삽입하면 어레이 리스트의 사이즈가 하나씩 증가한다.")
    @Test
    void size_test() {
        myLinkedList.add("안녕");
        myLinkedList.add("잘가");
        assertThat(myLinkedList.size()).isEqualTo(2);
    }

    @DisplayName("특정 인덱스의 값을 가져올 수 있다.")
    @Test
    void get_test() {
        myLinkedList.add("안녕");
        assertThat(myLinkedList.get(0)).isEqualTo("안녕");
    }

    @DisplayName("특정 인덱스의 값을 바꿀 수 있다.")
    @Test
    void set_test() {
        myLinkedList.add("안녕");
        myLinkedList.set(0, "잘가");
        assertThat(myLinkedList.contains("잘가")).isTrue();
        assertThat(myLinkedList.contains("안녕")).isFalse();
        assertThat(myLinkedList.get(0)).isEqualTo("잘가");
    }

    @DisplayName("지정한 값의 위치를 알 수 있다.")
    @Test
    void indexOf_test() {
        myLinkedList.add("안녕");
        myLinkedList.add("잘가");
        assertThat(myLinkedList.indexOf("안녕")).isEqualTo(0);
    }

    @DisplayName("리스트 내부가 비어있는지 확인할 수 있다.")
    @Test
    void isEmpty_test() {
        assertThat(myLinkedList.isEmpty()).isTrue();
    }

    @DisplayName("지정한 값을 삭제할 수 있다. 이때 값을 삭제한 경우에는 true를, 삭제하지 못한 경우에는 false를 반환한다.")
    @Test
    void remove_test() {
        myLinkedList.add("안녕");
        assertThat(myLinkedList.remove("안녕")).isTrue();
        assertThat(myLinkedList.remove("안녕")).isFalse();
    }

    @DisplayName("지정한 인덱스의 값을 삭제할 수 있다. 값을 삭제했다면 해당 원소를 반환한다.")
    @Test
    void remote_test2() {
        myLinkedList.add("안녕");
        assertThat(myLinkedList.remove(0)).isEqualTo("안녕");
    }

    @DisplayName("지정한 인덱스의 값을 삭제할 수 있다. 값이 없다면 Exception을 발생시킨다.")
    @Test
    void remote_test3() {
        assertThatThrownBy(() -> myLinkedList.remove(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @DisplayName("리스트의 값을 초기화할 수 있다.")
    @Test
    void clear_test() {
        myLinkedList.add("안녕");
        myLinkedList.add("잘가");
        myLinkedList.clear();
        assertThat(myLinkedList.isEmpty()).isTrue();
    }
}
