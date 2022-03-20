package blackjack.domain.game;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.participant.Player;

public class BlackjackGameTest {

    @Test
    @DisplayName("플레이어 턴이 끝났는지 체크")
    void isPlayersTurnEnd() {
        Player player = new Player("player", 0);
        Player player2 = new Player("player2", 0);
        BlackjackGame blackjackGame = new BlackjackGame(List.of(player, player2));

        while (!blackjackGame.getParticipants().isAllPlayerTurnEnd()) {
            blackjackGame.playPlayerTurn(false);
        }

        assertThat(blackjackGame.isPlayersTurnEnd()).isTrue();
    }
}
