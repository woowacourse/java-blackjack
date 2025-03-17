package blackjack.domain.player;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.BettingBoard;
import blackjack.domain.WinningStatus;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;

import blackjack.domain.money.BlackjackBettingMoney;

import blackjack.domain.money.Money;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayersTest {
    
    @Test
    void 플레이어들을_생성할_수_있다() {
        // given
        final List<String> playerNames = List.of("돔푸", "메이");
        
        // expected
        Assertions.assertDoesNotThrow(() -> Players.createPlayers(playerNames));
    }
    
    @Test
    void 중복된_플레이어가_존재하는_경우_예외를_발생시킨다() {
        // given
        final List<String> playerNames = List.of("돔푸", "메이", "돔푸");
        
        // expected
        assertThatThrownBy(() -> {Players.createPlayers(playerNames);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 중복될 수 없습니다.");
    }
    
    @ParameterizedTest
    @MethodSource("providePlayers")
    void 플레이어_인원수는_1명부터_6명까지_입니다(List<String> playerNames) {
        // expected
        assertThatThrownBy(() -> Players.createPlayers(playerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 인원수는 1명부터 6명까지 입니다.");
    }
    
    private static Stream<Arguments> providePlayers() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of("메이", "돔푸", "리사", "포라", "밍곰", "멍구", "가이온"))
        );
    }
    
    @Test
    void 플레이어들은_배팅을_할_수_있다() {
        // given
        Players players = Players.createPlayers(List.of("메이"));
        BettingBoard bettingBoard = new BettingBoard();
        Map<String, BlackjackBettingMoney> bettings = new HashMap<>();
        bettings.put("메이", new BlackjackBettingMoney(10000));
        
        // when
        players.bet(bettings, bettingBoard);
        
        // expected
        assertThat(bettingBoard.getProfit(new Player("메이"), WinningStatus.WIN))
                .isEqualTo(new Money(10000));
        
    }
    
    @Test
    void 플레이어들이_카드를_2장씩_뽑을_수_있다() {
        // given
        final List<String> playerNames = List.of("돔푸");
        Players players = Players.createPlayers(playerNames);
        
        // when
        List<PlayerBlackjackCardHand> cardHands = players.drawCardsAndGetCardHands(() -> List.of(HEART_1, HEART_2));
        
        // expected
        assertThat(cardHands.get(0).getCards()).containsExactly(HEART_1,HEART_2);
    }
}
