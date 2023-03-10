package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParserTest {

    @Test
    void 구분자를_기준으로_문자열을_파싱한다() {
        final String value = "pobi,jason";

        final List<String> result = Parser.parseByDelimiter(value, ",");

        assertThat(result).containsExactly("pobi", "jason");
    }

    @Test
    void 구분자를_기준으로_빈문자열을_파싱한다() {
        final String value = ",,";

        final List<String> result = Parser.parseByDelimiter(value, ",");

        assertThat(result).containsExactly("", "", "");
    }

    @Test
    void 문자열의_앞뒤_공백은_제거한다() {
        final List<String> value = List.of(" pobi", "jason ", " crong ");

        final List<String> result = Parser.trim(value);

        assertThat(result).containsExactly("pobi", "jason", "crong");
    }
}
