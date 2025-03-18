package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.ErrorException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameParticipantTest {

    @Test
    @DisplayName("이름이 중복되는 참여자 예외 테스트")
    void duplicateParticipantNamesTest() {
        // given
        GameParticipant gameParticipant = new GameParticipant();
        String names = "pobi,jason,pobi";
        // when & then
        assertThatThrownBy(() -> gameParticipant.registerPlayers(List.of(names.split(","))))
                .isInstanceOf(ErrorException.class)
                .hasMessageContaining("[ERROR]");
    }
}
