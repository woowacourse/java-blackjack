package domain.betting;

import domain.participant.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NameTest {

    @Test
    @DisplayName("양 옆 공백에 대한 케이스는 양옆의 공백을 없앤다.")
    void testTrimNameParseCase() {
        String playerName = " 1 ";
        Name name = new Name(playerName);

        assertThat(name).isEqualTo(new Name("1"));
    }


    @Test
    @DisplayName("공백 입력에 대한 케이스에 대해서는 예외를 반환한다.")
    void testEmptyNameParseCase() {
        String playerName = "  ";
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Name(playerName));
    }

}
