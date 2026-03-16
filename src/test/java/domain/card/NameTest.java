package domain.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"봉구스", "시오", "일이삼사오육칠팔", "pobi"})
    void 이름이_올바르게_생성된다(String value) {
        // given, when
        Name name = new Name(value);

        // then
        assertEquals(value, name.value());
    }

    @ParameterizedTest
    @ValueSource(strings = {"일", "일이삼사오육칠팔구", "a", "abcdefghi"})
    void 조건에_맞지_않는_입력인_경우에는_에러를_던진다(String value) {
        // given, when
        assertThrows(IllegalArgumentException.class, () -> new Name(value));

        // then
    }

}
