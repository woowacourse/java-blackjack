package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.game.TurnManager;
import blackjack.domain.participant.Player;

public class TurnManagerTest {

    @Test
    @DisplayName("턴이 끝났는지 검사 - 끝남")
    void isEndAllTurn() {
        List<Player> players = new ArrayList<>(List.of(new Player("마루")));
        TurnManager turnManager = new TurnManager(players);

        turnManager.turnToNext();

        assertThat(turnManager.isEndAllTurn()).isTrue();
    }

    @Test
    @DisplayName("턴이 끝났는지 검사 - 끝나지 않음")
    void isNotEndAllTurn() {
        List<Player> players = new ArrayList<>(List.of(new Player("마루")));
        TurnManager turnManager = new TurnManager(players);

        assertThat(turnManager.isEndAllTurn()).isFalse();
    }

    @Test
    @DisplayName("현재 턴인 플레이어 반환")
    void getCurrentPlayer() {
        List<Player> players = new ArrayList<>(List.of(new Player("마루")));
        TurnManager turnManager = new TurnManager(players);

        assertThat(turnManager.getCurrentPlayer()).isEqualTo(players.get(0));
    }

    @Test
    @DisplayName("턴이 모두 끝났을 때 플레이어 반환 금지")
    void getCurrentPlayerFail() {
        List<Player> players = new ArrayList<>(List.of(new Player("마루")));
        TurnManager turnManager = new TurnManager(players);

        turnManager.turnToNext();

        assertThatThrownBy(() -> {
            turnManager.getCurrentPlayer();
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
