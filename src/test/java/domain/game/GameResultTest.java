package domain.game;

import domain.participant.Participants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameResultTest {

    @Test
    @DisplayName("create()는 게임이 끝난 참가자들을 건네주면 게임 결과를 생성한다")
    void create_givenParticipants_thenSuccess() {
        List<String> playerName = List.of("a", "b", "c");
        Participants participants = Participants.create(playerName);

        GameResult gameResult = assertDoesNotThrow(() -> GameResult.create(participants));

        assertThat(gameResult)
                .isExactlyInstanceOf(GameResult.class);
    }
}
