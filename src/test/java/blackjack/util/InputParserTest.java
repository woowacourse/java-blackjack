package blackjack.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputParserTest {

    @Test
    @DisplayName("쉼표를 기준으로 분리하는 기능")
    void parsePlayer() {
        // given
        String input = "흑곰,밀란,로치";

        //when
        List<String> playerNames = InputParser.parsePlayer(input);

        //then
        assertThat(List.of("흑곰", "밀란", "로치")).isEqualTo(playerNames);
    }
}
