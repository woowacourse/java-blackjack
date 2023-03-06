package mission;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.SimpleTimeZone;

import static org.assertj.core.api.Assertions.assertThat;

public class ListStudy {
    @Test
    public void arrayListTest() {
        ArrayList<String> values = new ArrayList<>();
        values.add("first");
        values.add("second");

        assertThat(values.add("third")).isTrue(); // 세 번째 값을 추가한다.
        assertThat(values.size()).isEqualTo(3); // list의 크기를 구한다.
        assertThat(values.get(0)).isEqualTo("first"); // 첫 번째 값을 찾는다.
        assertThat(values.contains("first")).isTrue(); // "first" 값이 포함되어 있는지를 확인한다.
        assertThat(values.remove(0)).isEqualTo("first"); // 첫 번째 값을 삭제한다.
        assertThat(values.size()).isEqualTo(2); // 값이 삭제 됐는지 확인한다.

        values.forEach(System.out::println);
    }

    @Test
    public void simpleArrayListTest() {
        SimpleArrayList<String> values = new SimpleArrayList<>();
        values.add("first");
        values.add("second");
        values.add("third");
        values.add("fourth");
        values.add("fifth");


        assertThat(values.add("sixth")).isTrue(); //
        assertThat(values.size()).isEqualTo(6); // list의 크기를 구한다.
        assertThat(values.get(0)).isEqualTo("first"); // 첫 번째 값을 찾는다.
        assertThat(values.contains("first")).isTrue(); // "first" 값이 포함되어 있는지를 확인한다.
        assertThat(values.indexOf("second")).isEqualTo(1); // "second" 값의 인덱스를 찾는다.

        assertThat(values.remove(0)).isEqualTo("first"); // 첫 번째 값을 삭제한다.
        assertThat(values.size()).isEqualTo(5); // 값이 삭제 됐는지 확인한다.
        assertThat(values.remove("fourth")).isTrue(); //
        assertThat(values.size()).isEqualTo(4); // 값이 삭제 됐는지 확인한다.

        assertThat(values.indexOf("second")).isEqualTo(0); // "second" 값의 인덱스를 찾는다.
        assertThat(values.indexOf("fourth")).isEqualTo(-1); // "fourth" 값의 인덱스를 찾는다.
        assertThat(values.indexOf("sixth")).isEqualTo(3); // "sixth" 값의 인덱스를 찾는다.

        values.clear();
        assertThat(values.size()).isEqualTo(0);
    }

    @Test
    public void LinkedListTest() {
        LinkedList<String> values = new LinkedList<>();
        values.add("first");
        values.add("second");
        values.add("third");
        values.add("fourth");
        values.add("fifth");


        assertThat(values.add("sixth")).isTrue(); //
        assertThat(values.size()).isEqualTo(6); // list의 크기를 구한다.
        assertThat(values.get(0)).isEqualTo("first"); // 첫 번째 값을 찾는다.
        assertThat(values.contains("first")).isTrue(); // "first" 값이 포함되어 있는지를 확인한다.
        assertThat(values.indexOf("second")).isEqualTo(1); // "second" 값의 인덱스를 찾는다.

        assertThat(values.remove(0)).isEqualTo("first"); // 첫 번째 값을 삭제한다.
        assertThat(values.size()).isEqualTo(5); // 값이 삭제 됐는지 확인한다.
        assertThat(values.remove("fourth")).isTrue(); //
        assertThat(values.size()).isEqualTo(4); // 값이 삭제 됐는지 확인한다.

        assertThat(values.indexOf("second")).isEqualTo(0); // "second" 값의 인덱스를 찾는다.
        assertThat(values.indexOf("fourth")).isEqualTo(-1); // "fourth" 값의 인덱스를 찾는다.
        assertThat(values.indexOf("sixth")).isEqualTo(3); // "sixth" 값의 인덱스를 찾는다.

        values.clear();
        assertThat(values.size()).isEqualTo(0);
    }

    @Test
    public void simpleLinkedListTest() {
        SimpleLinkedList<String> values = new SimpleLinkedList<>();
        values.add("first");
        values.add("second");
        values.add("third");
        values.add("fourth");
        values.add("fifth");


        assertThat(values.add("sixth")).isTrue(); //
        assertThat(values.size()).isEqualTo(6); // list의 크기를 구한다.
        assertThat(values.get(0)).isEqualTo("first"); // 첫 번째 값을 찾는다.
        assertThat(values.contains("first")).isTrue(); // "first" 값이 포함되어 있는지를 확인한다.
        assertThat(values.indexOf("second")).isEqualTo(1); // "second" 값의 인덱스를 찾는다.

        assertThat(values.remove(0)).isEqualTo("first"); // 첫 번째 값을 삭제한다.
        assertThat(values.size()).isEqualTo(5); // 값이 삭제 됐는지 확인한다.
        assertThat(values.remove("fourth")).isTrue(); //
        assertThat(values.size()).isEqualTo(4); // 값이 삭제 됐는지 확인한다.

        assertThat(values.indexOf("second")).isEqualTo(0); // "second" 값의 인덱스를 찾는다.
        assertThat(values.indexOf("fourth")).isEqualTo(-1); // "fourth" 값의 인덱스를 찾는다.
        assertThat(values.indexOf("sixth")).isEqualTo(3); // "sixth" 값의 인덱스를 찾는다.

        values.clear();
        assertThat(values.size()).isEqualTo(0);
    }
}
