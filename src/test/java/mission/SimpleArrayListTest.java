package mission;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class SimpleArrayListTest {

    private SimpleList<String> sut;

    @BeforeEach
    void setUp() {
        sut = new SimpleArrayList<>();
    }

    @Test
    void 값을_추가한다() {
        sut.add("hello");

        assertThat(sut.size()).isEqualTo(1);
    }

    @Test
    void 입력한_인덱스에_값을_추가한다() {
        sut.add("hello1");
        sut.add("hello3");

        sut.add(1, "hello2");

        assertThat(sut.get(1)).isEqualTo("hello2");
    }

    @Test
    void 입력한_인덱스의_값을_변경한다() {
        sut.add("hello1");

        sut.set(0, "hello2");

        assertThat(sut.get(0)).isEqualTo("hello2");
    }

    @Test
    void 값을_변경할_때_해당_인덱스가_존재하지_않는다면_예외를_던진다() {
        assertThatThrownBy(() -> sut.set(0, "hello"))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void 값을_가져온다() {
        sut.add("hello");

        assertThat(sut.get(0)).isEqualTo("hello");
    }

    @Test
    void 값을_가져올_때_해당_인덱스가_존재하지_않는다면_예외를_던진다() {
        assertThatThrownBy(() -> sut.get(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @ParameterizedTest(name = "입력한 값이 존재하는지 확인한다. 리스트: [\"hello\"] 입력:{0}, 결과:{1}")
    @CsvSource({"hello, true", "hi, false"})
    void 입력한_값이_존재하는지_확인한다(final String value, final boolean result) {
        sut.add("hello");

        assertThat(sut.contains(value)).isEqualTo(result);
    }

    @Test
    void 입력값에_대한_인덱스를_반환한다() {
        sut.add("hello1");
        sut.add("hello2");

        assertThat(sut.indexOf("hello2")).isEqualTo(1);
    }

    @Test
    void 입력값이_존재하지_않는다면_음수_1을_반환한다() {
        assertThat(sut.indexOf("hello")).isEqualTo(-1);
    }

    @Test
    void 모든_값을_삭제한다() {
        sut.add("hello1");

        sut.clear();

        assertThat(sut.isEmpty()).isTrue();
    }

    @Test
    void 값이_없는지_확인한다() {
        assertThat(sut.isEmpty()).isTrue();
    }

    @Test
    void 입력한_인덱스의_값을_삭제한다() {
        sut.add("hello1");
        sut.add("hello2");
        sut.add("hello3");

        sut.remove(1);

        assertThat(sut.size()).isEqualTo(2);
    }

    @Test
    void 삭제할_인덱스가_존재하지_않는다면_예외를_던진다() {
        assertThatThrownBy(() -> sut.remove(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void 입력한_값을_삭제한다() {
        sut.add("hello1");
        sut.add("hello2");
        sut.add("hello3");

        sut.remove("hello2");

        assertThat(sut.size()).isEqualTo(2);
    }
}
