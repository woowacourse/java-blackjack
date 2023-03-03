package mission;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * mission 패키지는
 * 우아한 테크코스 미니 미션입니다.
 * 추가적으로 리뷰 하실 필요는 없습니다.
 */
public class ListStudy {
    @ParameterizedTest
    @MethodSource("provider")
    public void arrayListTest(SimpleList values) {
        values.add("first");
        values.add("second");

        values.add(0, "zero"); // 첫 번째 값을 추가한다.

        assertThat(values.add("third")).isTrue(); // 네 번째 값을 추가한다.
        assertThat(values.set(2, "changedSecond")).isEqualTo("second"); // 두 번째 값을 변경한다.
        assertThat(values.get(2)).isEqualTo("changedSecond"); // 변경된 두 번 째 값을 확인한다.
        assertThat(values.size()).isEqualTo(4); // list의 크기를 구한다.
        assertThat(values.get(0)).isEqualTo("zero"); // 첫 번째 값을 찾는다.
        assertThat(values.contains("first")).isTrue(); // "first" 값이 포함되어 있는지를 확인한다.
        assertThat(values.remove(0)).isEqualTo("zero"); // 첫 번째 값을 삭제한다.

        assertThat(values.size()).isEqualTo(3); // 값이 삭제 됐는지 확인한다.
        assertThat(values.isEmpty()).isFalse(); // 값이 비어있지 않음을 확인한다.
        assertThat(values.remove("first")).isTrue(); // "first"값을 삭제한다.
        assertThat(values.size()).isEqualTo(2); // 값이 삭제 됐는지 확인한다.
        assertThat(values.toString()).isEqualTo("[changedSecond, third]"); // values에 담긴 모든 값을 출력한다.
        values.clear(); // 리스트를 초기화 한다.
        assertThat(values.isEmpty()).isTrue(); // 값이 비어있음을 확인한다.
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.arguments(new SimpleArrayList()),
                Arguments.arguments(new SimpleLinkedList())
        );
    }
}
