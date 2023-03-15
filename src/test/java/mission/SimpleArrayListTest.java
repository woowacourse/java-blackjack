package mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/* 출처: 허브코딩 */

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class SimpleArrayListTest {

    private SimpleList<String> testList;

    @BeforeEach
    void setUp() {
        testList = new SimpleArrayList<>();
    }

    @Test
    void 값을_추가한다() {
        testList.add("hello");

        assertThat(testList.size()).isEqualTo(1);
    }

    @Test
    void 입력한_인덱스에_값을_추가한다() {
        testList.add("hello1");
        testList.add("hello3");

        testList.add(1, "hello2");

        assertThat(testList.get(1)).isEqualTo("hello2");
    }

    @Test
    void 입력한_인덱스의_값을_변경한다() {
        testList.add("hello1");

        testList.set(0, "hello2");

        assertThat(testList.get(0)).isEqualTo("hello2");
    }

    @Test
    void 값을_가져온다() {
        testList.add("hello");

        assertThat(testList.get(0)).isEqualTo("hello");
    }

    @Test
    void 값을_가져올_때_해당_인덱스가_존재하지_않는다면_예외를_던진다() {
        assertThatThrownBy(() -> testList.get(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void 입력값에_대한_인덱스를_반환한다() {
        testList.add("hello1");
        testList.add("hello2");

        assertThat(testList.indexOf("hello2")).isEqualTo(1);
    }

    @Test
    void 입력값이_존재하지_않는다면_음수_1을_반환한다() {
        assertThat(testList.indexOf("hello")).isEqualTo(-1);
    }

    @Test
    void 모든_값을_삭제한다() {
        testList.add("hello1");

        testList.clear();

        assertThat(testList.isEmpty()).isTrue();
    }

    @Test
    void 값이_없는지_확인한다() {
        assertThat(testList.isEmpty()).isTrue();
    }

    @Test
    void 입력한_인덱스의_값을_삭제한다() {
        testList.add("hello1");
        testList.add("hello2");
        testList.add("hello3");

        testList.remove(1);

        assertThat(testList.size()).isEqualTo(2);
    }

    @Test
    void 삭제할_인덱스가_존재하지_않는다면_예외를_던진다() {
        assertThatThrownBy(() -> testList.remove(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void 입력한_값을_삭제한다() {
        testList.add("hello1");
        testList.add("hello2");
        testList.add("hello3");

        testList.remove("hello2");

        assertThat(testList.size()).isEqualTo(2);
    }
}
