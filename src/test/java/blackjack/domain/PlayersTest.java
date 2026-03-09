package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.dto.PlayerResult;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {
    
    @DisplayName("이름 목록으로 플레이어 일급 컬렉션을 생성한다.")
    @Test
    void createPlayers() {
        Players players = Players.makePlayers(List.of("pobi", "jason"));
        
        assertThat(players.getAllPlayers()).hasSize(2);
    }
    
    @DisplayName("이름이 공백일 경우 예외가 발생한다.")
    @Test
    void validateEmptyName() {
        assertThatThrownBy(() -> Players.makePlayers(List.of("pobi", "")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 공백이 될 수 없습니다.");
    }
    
    @DisplayName("카드를 받을 수 있는 플레이어를 순서대로 찾는다.")
    @Test
    void findDrawablePlayer() {
        Players players = Players.makePlayers(List.of("pobi", "jason"));
        
        assertThat(players.findDrawablePlayerNickname()).isEqualTo("pobi");
        
        players.dontWantDraw();
        assertThat(players.findDrawablePlayerNickname()).isEqualTo("jason");
        
        players.dontWantDraw();
        assertThat(players.findDrawablePlayerNickname()).isNull();
    }
    
    @DisplayName("버스트된 플레이어는 건너뛰고 다음 드로우 가능 플레이어를 찾는다.")
    @Test
    void skipBustedPlayerWhenFindingDrawable() {
        Players players = Players.makePlayers(List.of("pobi", "jason"));
        PlayingCards bustedCards = PlayingCards.from(List.of(
                new Card(Rank.TEN, Suit.SPADE),
                new Card(Rank.TEN, Suit.HEART),
                new Card(Rank.TWO, Suit.DIAMOND)
        ));
        
        players.addCardToAvailablePlayer(bustedCards);
        
        assertThat(players.findDrawablePlayerNickname()).isEqualTo("jason");
    }
    
    @DisplayName("모든 플레이어가 카드를 받을 수 없는 상태면 null을 반환한다.")
    @Test
    void findDrawablePlayerReturnsNullWhenAllDone() {
        Players players = Players.makePlayers(List.of("pobi"));
        
        players.dontWantDraw();
        
        assertThat(players.findDrawablePlayerNickname()).isNull();
    }
    
    @DisplayName("플레이어가 딜러보다 점수가 높으면 승리로 기록되어야 한다.")
    @Test
    void determinePlayerResultsPlayerWins() {
        Players players = Players.makePlayers(List.of("pobi"));
        PlayingCards playerCards = PlayingCards.from(List.of(
                new Card(Rank.TEN, Suit.SPADE),
                new Card(Rank.TEN, Suit.HEART)
        ));
        players.addCardToAvailablePlayer(playerCards);
        
        List<PlayerResult> results = players.getWinningResults(18);
        PlayerResult pobiResult = results.getFirst();
        
        assertThat(pobiResult.gameResult()).isEqualTo(GameResult.WIN);
        assertThat(pobiResult.gameResult()).isNotEqualTo(GameResult.DRAW);
        assertThat(pobiResult.gameResult()).isNotEqualTo(GameResult.LOSE);
    }
    
    @DisplayName("플레이어가 딜러보다 점수가 낮으면 패배로 기록되어야 한다.")
    @Test
    void determinePlayerResultsPlayerLoses() {
        Players players = Players.makePlayers(List.of("pobi"));
        PlayingCards playerCards = PlayingCards.from(List.of(
                new Card(Rank.TEN, Suit.SPADE),
                new Card(Rank.SEVEN, Suit.HEART)
        ));
        players.addCardToAvailablePlayer(playerCards);
        
        List<PlayerResult> results = players.getWinningResults(20);
        PlayerResult pobiResult = results.getFirst();
        
        assertThat(pobiResult.gameResult()).isEqualTo(GameResult.LOSE);
        assertThat(pobiResult.gameResult()).isNotEqualTo(GameResult.WIN);
        assertThat(pobiResult.gameResult()).isNotEqualTo(GameResult.DRAW);
    }
}
