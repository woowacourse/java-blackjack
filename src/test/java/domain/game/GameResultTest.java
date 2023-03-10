package domain.game;

import domain.participant.Participant;
import domain.participant.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class GameResultTest {

    @Test
    @DisplayName("create()는 게임이 끝난 참가자들을 건네주면 게임 결과를 생성한다")
    void create_givenParticipants_thenSuccess() {
        // given
        final List<String> playerName = List.of("a", "b", "c");
        final Players players = Players.create(playerName);
        final Participant dealer = Participant.createDealer();

        // when, then
        final GameResult gameResult = assertDoesNotThrow(() ->
                GameResult.create(dealer, players.getPlayers()));

        assertThat(gameResult)
                .isExactlyInstanceOf(GameResult.class);
    }
}
