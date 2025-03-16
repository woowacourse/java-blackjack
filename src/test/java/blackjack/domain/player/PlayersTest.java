package blackjack.domain.player;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card_hand.PlayerBlackjackCardHand;
import blackjack.test_util.TestConstants;

import java.util.ArrayList;
import java.util.List;
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
    void 플레이어_이름으로_플레이어를_찾을_수_있다() {
        // given
        final List<String> playerNames = List.of("돔푸", "메이");
        Players players = Players.createPlayers(playerNames);
        
        // expected
        assertThat(players.findPlayerByName("메이")).isEqualTo(new Player("메이"));
    }
    
    @Test
    void 존재하지_않는_플레이어를_찾으려는_경우_예외를_발생시킨다() {
        // given
        final List<String> playerNames = List.of("돔푸", "메이");
        Players players = Players.createPlayers(playerNames);
        
        // expected
        assertThatThrownBy(() -> players.findPlayerByName("멍구"))
                .isInstanceOf(IllegalArgumentException.class);
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
