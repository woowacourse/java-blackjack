package list;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class ListStudy {

    @Test
    public void arrayList() {
        SimpleArrayList values = new SimpleArrayList();
        values.add("first");
        values.add("second");

        assertThat(values.add("third")).isTrue(); // 세 번째 값을 추가한다.

        assertThatCode(() -> values.add(0, "zero")).doesNotThrowAnyException(); // 맨 앞에 0을 추가한다.
        assertThatCode(() -> values.add(100, "hundred")).isInstanceOf(RuntimeException.class); // 인덱스가 넘어가면 예외가 발생한다.

        assertThat(values.set(0, "0")).isEqualTo("0"); // 첫번째 원소가 "0"으로 바뀐다.
        assertThatCode(() -> values.set(100, "100")).isInstanceOf(RuntimeException.class); // 인덱스가 넘어가면 예외가 발생한다.

        assertThat(values.get(0)).isEqualTo("0"); // 첫 번째 값을 찾는다.
        assertThatCode(() -> values.get(100)).isInstanceOf(RuntimeException.class); // 인덱스가 넘어가면 예외가 발생한다.

        assertThat(values.contains("first")).isTrue(); // "first" 값이 포함되어 있는지를 확인한다.
        assertThat(values.contains("fifth")).isFalse(); // "fifth" 값이 포함되어있지 않은지를 확인한다.

        assertThat(values.indexOf("second")).isEqualTo(2); // "second" 값의 index가 2인지 확인한다.
        assertThat(values.indexOf("fifth")).isEqualTo(-1); // 존재하지 않는 값을 조회하면 -1을 반환하는지 확인한다.

        assertThat(values.size()).isEqualTo(4); // list의 크기를 구한다.

        assertThat(values.isEmpty()).isFalse(); // list가 비어있지 않다고 나오는지 확인한다.
        assertThat(new SimpleArrayList().isEmpty()).isTrue(); // 초기 리스트가 비어있는지 확인한다.

        assertThat(values.remove("first")).isTrue(); // list에서 "first"를 삭제한다.
        assertThat(values.size()).isEqualTo(3); // 값이 삭제 됐는지 확인한다.
        assertThat(values.remove("fifth")).isFalse(); // 없는 원소를 제거하려하면 "false"를 반환한다.

        assertThat(values.remove(0)).isEqualTo("0"); // 첫 번째 값을 삭제한다.
        assertThat(values.size()).isEqualTo(2); // 값이 삭제 됐는지 확인한다.
        assertThatCode(() -> values.remove(100)).isInstanceOf(RuntimeException.class); // 인덱스가 넘어가면 예외가 발생한다.

        // TODO values에 담긴 모든 값을 출력한다.
        values.printAll();

        assertThatCode(values::clear).doesNotThrowAnyException(); // 리스트를 초기화한다.
        assertThat(values.isEmpty()).isTrue(); // 초기화되었는지 확인한다.
    }

    @Test
    public void linkedList() {
        SimpleLinkedList values = new SimpleLinkedList();
        values.add("first");
        values.add("second");

        assertThat(values.add("third")).isTrue(); // 세 번째 값을 추가한다.

        assertThatCode(() -> values.add(0, "zero")).doesNotThrowAnyException(); // 맨 앞에 0을 추가한다.
        assertThatCode(() -> values.add(100, "hundred")).isInstanceOf(RuntimeException.class); // 인덱스가 넘어가면 예외가 발생한다.

        assertThat(values.set(0, "0")).isEqualTo("0"); // 첫번째 원소가 "0"으로 바뀐다.
        assertThatCode(() -> values.set(100, "100")).isInstanceOf(RuntimeException.class); // 인덱스가 넘어가면 예외가 발생한다.

        assertThat(values.get(0)).isEqualTo("0"); // 첫 번째 값을 찾는다.
        assertThatCode(() -> values.get(100)).isInstanceOf(RuntimeException.class); // 인덱스가 넘어가면 예외가 발생한다.

        assertThat(values.contains("first")).isTrue(); // "first" 값이 포함되어 있는지를 확인한다.
        assertThat(values.contains("fifth")).isFalse(); // "fifth" 값이 포함되어있지 않은지를 확인한다.

        assertThat(values.indexOf("second")).isEqualTo(2); // "second" 값의 index가 2인지 확인한다.
        assertThat(values.indexOf("fifth")).isEqualTo(-1); // 존재하지 않는 값을 조회하면 -1을 반환하는지 확인한다.

        assertThat(values.size()).isEqualTo(4); // list의 크기를 구한다.

        assertThat(values.isEmpty()).isFalse(); // list가 비어있지 않다고 나오는지 확인한다.
        assertThat(new SimpleArrayList().isEmpty()).isTrue(); // 초기 리스트가 비어있는지 확인한다.

        assertThat(values.remove("first")).isTrue(); // list에서 "first"를 삭제한다.
        assertThat(values.size()).isEqualTo(3); // 값이 삭제 됐는지 확인한다.
        assertThat(values.remove("fifth")).isFalse(); // 없는 원소를 제거하려하면 "false"를 반환한다.

        assertThat(values.remove(0)).isEqualTo("0"); // 첫 번째 값을 삭제한다.
        assertThat(values.size()).isEqualTo(2); // 값이 삭제 됐는지 확인한다.
        assertThatCode(() -> values.remove(100)).isInstanceOf(RuntimeException.class); // 인덱스가 넘어가면 예외가 발생한다.

        // TODO values에 담긴 모든 값을 출력한다.
        values.printAll();

        assertThatCode(values::clear).doesNotThrowAnyException(); // 리스트를 초기화한다.
        assertThat(values.isEmpty()).isTrue(); // 초기화되었는지 확인한다.
    }
}
