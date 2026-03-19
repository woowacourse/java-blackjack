package util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameParserTest {
    @Test
    @DisplayName("이름 문자열을 쉼표로 분리해 플레이어 수를 만든다")
    void makePlayersSize() {
        List<String> names = NameParser.makeNameList("pobi,jason");

        assertEquals(2, names.size());
    }

    @Test
    @DisplayName("쉼표 뒤 공백을 제거해 이름을 저장한다")
    void trimPlayerName() {
        List<String> names = NameParser.makeNameList("pobi, jason");

        assertEquals("jason", names.get(1));
    }

    @Test
    @DisplayName("입력된 이름 중 중복되는 이름이 존재하면 예외가 발생한다")
    void throwExceptionWhenNameIsDuplicated() {
        assertThatThrownBy(() -> NameParser.makeNameList("pobi,pobi"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름은 허용하지 않습니다.");
    }
}
