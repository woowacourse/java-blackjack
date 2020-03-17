package domain.gamer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayerNamesParserTest {

    @Test
    void parseValidPlayerNames() {
        String playerNamesValue = "a,b,c";
        assertThat(PlayerNamesParser.parse(playerNamesValue)).isEqualTo(Arrays.asList("a","b","c"));
    }

    @Test
    void parseInvalidPlayerNames() {
        String playerNamesValue = "1,2,3";
        assertThatThrownBy(() -> PlayerNamesParser.parse(playerNamesValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PlayerNamesParser.NOT_ALPHABETIC_ERROR);
    }
}