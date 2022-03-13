package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class TurnManagerTest {

    @Test
    @DisplayName("턴이 끝났는지 검사 - 끝남")
    void isEndAllTurn() {
        Player player = new Player("마루");
        player.initCards(List.of(new Card(Suit.DIAMOND, Denomination.SEVEN),
            new Card(Suit.CLOVER, Denomination.TEN),
            new Card(Suit.CLOVER, Denomination.EIGHT)));
        List<Player> players = new ArrayList<>(List.of(player));
        Participants participants = new Participants(players);
        GameResult gameResult = new GameResult(participants);
        gameResult.getPlayerResult().put(player, WinningResult.WIN);
        TurnManager turnManager = new TurnManager(players, gameResult);

        turnManager.turnToNext(gameResult);

        assertThat(turnManager.isEndAllTurn()).isTrue();
    }

    @Test
    @DisplayName("턴이 끝났는지 검사 - 끝나지 않음")
    void isNotEndAllTurn() {
        List<Player> players = new ArrayList<>(List.of(new Player("마루")));
        Participants participants = new Participants(players);
        GameResult gameResult = new GameResult(participants);
        TurnManager turnManager = new TurnManager(players, gameResult);

        assertThat(turnManager.isEndAllTurn()).isFalse();
    }

    @Test
    @DisplayName("현재 턴인 플레이어 반환")
    void getCurrentPlayer() {
        List<Player> players = new ArrayList<>(List.of(new Player("마루")));
        Participants participants = new Participants(players);
        GameResult gameResult = new GameResult(participants);
        TurnManager turnManager = new TurnManager(players, gameResult);

        assertThat(turnManager.getCurrentPlayer()).isEqualTo(players.get(0));
    }

    @Test
    @DisplayName("턴이 모두 끝났을 때 플레이어 반환 금지")
    void getCurrentPlayerFail() {
        Player player = new Player("마루");
        player.initCards(List.of(new Card(Suit.DIAMOND, Denomination.SEVEN),
            new Card(Suit.CLOVER, Denomination.TEN),
            new Card(Suit.CLOVER, Denomination.EIGHT)));
        List<Player> players = new ArrayList<>(List.of(player));
        Participants participants = new Participants(players);
        GameResult gameResult = new GameResult(participants);
        gameResult.getPlayerResult().put(player, WinningResult.WIN);
        TurnManager turnManager = new TurnManager(players, gameResult);

        turnManager.turnToNext(gameResult);

        assertThatThrownBy(() -> {
            turnManager.getCurrentPlayer();
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
