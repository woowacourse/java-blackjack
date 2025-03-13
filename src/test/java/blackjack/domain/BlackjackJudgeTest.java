package blackjack.domain;

import blackjack.domain.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static blackjack.test_util.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card_hand.DealerBlackjackCardHand;
import blackjack.domain.card_hand.PlayerBlackjackCardHand;
import blackjack.domain.deck.BlackjackDeck;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BlackjackJudgeTest {
    
    @Test
    void 생성자의_파라미터가_NULL_이면_예외를_발생시킨다() {
        // given
        final List<PlayerBlackjackCardHand> playerBlackjackCardHands = List.of(new PlayerBlackjackCardHand(DEFAULT_PLAYER, List::of));
        final DealerBlackjackCardHand dealerBlackjackCardHand = new DealerBlackjackCardHand(List::of);
        
        // expected
        assertAll(
            () -> assertThatThrownBy(() -> new BlackjackJudge(null, playerBlackjackCardHands))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("딜러의 손패는 null이 될 수 없습니다."),
            () -> assertThatThrownBy(() -> new BlackjackJudge(dealerBlackjackCardHand, null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플래이어의 손패는 null이 될 수 없습니다.")
        );
    }

    @Test
    void 블랙잭심판은_딜러의_손패와_플레이들의_손패들로_생성된다() {
        // given
        final BlackjackDeck deck = new BlackjackDeck();
        final Player dompoo = new Player("dompoo");
        final Player may = new Player("may");
        List<PlayerBlackjackCardHand> playerHands = List.of(new PlayerBlackjackCardHand(dompoo, deck), new PlayerBlackjackCardHand(may, deck));
        final DealerBlackjackCardHand dealerHand = new DealerBlackjackCardHand(deck);
        
        // expected
        assertDoesNotThrow(() -> new BlackjackJudge(dealerHand, playerHands));
    }
    
    @Test
    void 블랙잭심판은_딜러의_블랙잭_승리_갯수를_확인할_수_있다() {
        // given
        final PlayerBlackjackCardHand dompooCardHand = new PlayerBlackjackCardHand(new Player("dompoo"), () -> List.of(DIAMOND_1, HEART_7));
        final PlayerBlackjackCardHand mayCardHand = new PlayerBlackjackCardHand(new Player("may"), () -> List.of(HEART_2, HEART_3));
        final List<PlayerBlackjackCardHand> playerHands = List.of(dompooCardHand, mayCardHand);
        final DealerBlackjackCardHand dealerHand = new DealerBlackjackCardHand(() -> List.of(HEART_1, DIAMOND_10));
        
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerHand, playerHands);
        
        // expected
        assertThat(blackjackJudge.getDealerBlackjackWinningCount()).isEqualTo(2);
    }
    
    @Test
    void 블랙잭심판은_딜러의_승리_갯수를_확인할_수_있다() {
        // given
        final PlayerBlackjackCardHand dompooCardHand = new PlayerBlackjackCardHand(new Player("dompoo"), () -> List.of(DIAMOND_1, HEART_7));
        final PlayerBlackjackCardHand mayCardHand = new PlayerBlackjackCardHand(new Player("may"), () -> List.of(HEART_2, HEART_3));
        final List<PlayerBlackjackCardHand> playerHands = List.of(dompooCardHand, mayCardHand);
        final DealerBlackjackCardHand dealerHand = new DealerBlackjackCardHand(() -> List.of(DIAMOND_1, HEART_5));
        
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerHand, playerHands);
        
        // expected
        assertThat(blackjackJudge.getDealerWinningCount()).isEqualTo(1);
    }
    
    @Test
    void 블랙잭심판은_딜러의_패배_갯수를_확인할_수_있다() {
        // given
        final PlayerBlackjackCardHand dompooCardHand = new PlayerBlackjackCardHand(new Player("dompoo"), () -> List.of(DIAMOND_1, HEART_7));
        final PlayerBlackjackCardHand mayCardHand = new PlayerBlackjackCardHand(new Player("may"), () -> List.of(HEART_2, HEART_3));
        final PlayerBlackjackCardHand lisaCardHand = new PlayerBlackjackCardHand(new Player("lisa"), () -> List.of(DIAMOND_1, HEART_8));
        final List<PlayerBlackjackCardHand> playerHands = List.of(dompooCardHand, mayCardHand, lisaCardHand);
        final DealerBlackjackCardHand dealerHand = new DealerBlackjackCardHand(() -> List.of(DIAMOND_1, HEART_5));
        
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerHand, playerHands);
        
        // expected
        assertThat(blackjackJudge.getDealerLosingCount()).isEqualTo(2);
    }
    
    @Test
    void 블랙잭심판은_딜러의_무승부_갯수를_확인할_수_있다() {
        // given
        final PlayerBlackjackCardHand dompooCardHand = new PlayerBlackjackCardHand(new Player("dompoo"), () -> List.of(HEART_10, HEART_6));
        final PlayerBlackjackCardHand mayCardHand = new PlayerBlackjackCardHand(new Player("may"), () -> List.of(HEART_2, HEART_3));
        final PlayerBlackjackCardHand lisaCardHand = new PlayerBlackjackCardHand(new Player("lisa"), () -> List.of(DIAMOND_1, HEART_8));
        final List<PlayerBlackjackCardHand> playerHands = List.of(dompooCardHand, mayCardHand, lisaCardHand);
        final DealerBlackjackCardHand dealerHand = new DealerBlackjackCardHand(() -> List.of(DIAMOND_1, HEART_5));
        
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerHand, playerHands);
        
        // expected
        assertThat(blackjackJudge.getDealerDrawingCount()).isEqualTo(1);
    }
    
    @Test
    void 한_플레이어의_승패여부를_확인할_때_존재하지_않는_플레이어라면_예외가_발생한다() {
        // given
        final PlayerBlackjackCardHand dompooCardHand = new PlayerBlackjackCardHand(new Player("dompoo"), () -> List.of(HEART_10, HEART_6));
        final PlayerBlackjackCardHand mayCardHand = new PlayerBlackjackCardHand(new Player("may"), () -> List.of(HEART_2, HEART_3));
        final List<PlayerBlackjackCardHand> playerHands = List.of(dompooCardHand, mayCardHand);
        final DealerBlackjackCardHand dealerHand = new DealerBlackjackCardHand(() -> List.of(DIAMOND_1, HEART_5));
        
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerHand, playerHands);
        
        final PlayerBlackjackCardHand lisaCardHand = new PlayerBlackjackCardHand(new Player("lisa"), List::of);
        // expected
        assertThatThrownBy(() -> blackjackJudge.getWinningStatusOf(lisaCardHand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 플레이어 손패입니다.");
    }
    
    @ParameterizedTest
    @MethodSource("provideCardsAndWinningStatus")
    void 블랙잭심판은_한_플레이어의_승패_여부를_확인할_수_있다(List<Card> cards, WinningStatus winningStatus) {
        // given
        final PlayerBlackjackCardHand dompooCardHand = new PlayerBlackjackCardHand(new Player("dompoo"), () -> cards);
        List<PlayerBlackjackCardHand> playerHands = List.of(dompooCardHand);
        final DealerBlackjackCardHand dealerHand = new DealerBlackjackCardHand(() -> List.of(DIAMOND_1, HEART_5));

        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerHand, playerHands);

        // expected
        assertThat(blackjackJudge.getWinningStatusOf(dompooCardHand)).isEqualTo(winningStatus);
    }
    
    private static Stream<Arguments> provideCardsAndWinningStatus() {
        return Stream.of(
                Arguments.of(List.of(DIAMOND_10, DIAMOND_9), WinningStatus.WIN),
                Arguments.of(List.of(DIAMOND_10, HEART_6), WinningStatus.DRAW),
                Arguments.of(List.of(DIAMOND_10, HEART_5), WinningStatus.LOSE)
        );
    }
    
    @Test
    void 블랙잭심판은_모든_플레이어의_승패_결과를_한_번에_알_수_있다() {
        // given
        Player firstPlayer = new Player("dompoo");
        Player secondPlayer = new Player("may");
        
        PlayerBlackjackCardHand firstPlayerCardHand = new PlayerBlackjackCardHand(firstPlayer, () -> List.of(DIAMOND_1, DIAMOND_10));
        PlayerBlackjackCardHand secondPlayerCardHand = new PlayerBlackjackCardHand(secondPlayer, () -> List.of(HEART_1, HEART_5));
        List<PlayerBlackjackCardHand> playerCardHands = List.of(firstPlayerCardHand, secondPlayerCardHand);
        
        // when
        DealerBlackjackCardHand dealerBlackjackCardHand = new DealerBlackjackCardHand(() -> List.of(HEART_8, HEART_9));
        BlackjackJudge blackjackJudge = new BlackjackJudge(dealerBlackjackCardHand, playerCardHands);
        
        // then
        Map<Player, WinningStatus> expected = new HashMap<>();
        expected.put(firstPlayer, blackjackJudge.getWinningStatusOf(firstPlayerCardHand));
        expected.put(secondPlayer, blackjackJudge.getWinningStatusOf(secondPlayerCardHand));
        
        Assertions.assertThat(blackjackJudge.getWinningStatusOfAllPlayers())
                .containsAllEntriesOf(expected);
    }
}
