package blackjack.domain;

import blackjack.domain.card_hand.DealerCardHand;
import blackjack.domain.card_hand.PlayerCardHand;
import blackjack.testutil.CardHandInitializerDummy;
import blackjack.testutil.CardHandInitializerStub;
import blackjack.testutil.TestConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static blackjack.testutil.TestConstants.*;
import static org.assertj.core.api.Assertions.*;

public class BlackjackJudgeTest {

    @Test
    void 블랙잭심판은_딜러의_손패와_플레이들의_손패들로_생성된다() {
        // given
        final Deck deck = new Deck();
        final Player dompoo = new Player("dompoo");
        final Player may = new Player("may");
        List<PlayerCardHand> playerHands = List.of(new PlayerCardHand(dompoo, deck), new PlayerCardHand(may, deck));
        final DealerCardHand dealerHand = new DealerCardHand(deck);
        
        // expected
        Assertions.assertDoesNotThrow(() -> new BlackjackJudge(dealerHand, playerHands));
    }
    
    @Test
    void 블랙잭심판은_딜러의_승리_갯수를_확인할_수_있다() {
        // given
        List<Card> dompooCards = List.of(DIAMOND_1, HEART_7);
        List<Card> mayCards = List.of(HEART_2, HEART_3);
        List<Card> dealerCards = List.of(DIAMOND_1, HEART_5);
        
        final PlayerCardHand dompooCardHand = new PlayerCardHand(new Player("dompoo"), new CardHandInitializerStub(dompooCards));
        final PlayerCardHand mayCardHand = new PlayerCardHand(new Player("may"), new CardHandInitializerStub(mayCards));
        List<PlayerCardHand> playerHands = List.of(dompooCardHand, mayCardHand);
        final DealerCardHand dealerHand = new DealerCardHand(new CardHandInitializerStub(dealerCards));
        
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerHand, playerHands);
        
        // expected
        assertThat(blackjackJudge.getDealerWinningCount()).isEqualTo(1);
    }
    
    @Test
    void 블랙잭심판은_딜러의_패배_갯수를_확인할_수_있다() {
        // given
        List<Card> dompooCards = List.of(DIAMOND_1, HEART_7);
        List<Card> mayCards = List.of(HEART_2, HEART_3);
        List<Card> lisaCards = List.of(DIAMOND_1, HEART_8);
        List<Card> dealerCards = List.of(DIAMOND_1, HEART_5);
        
        final PlayerCardHand dompooCardHand = new PlayerCardHand(new Player("dompoo"), new CardHandInitializerStub(dompooCards));
        final PlayerCardHand mayCardHand = new PlayerCardHand(new Player("may"), new CardHandInitializerStub(mayCards));
        final PlayerCardHand lisaCardHand = new PlayerCardHand(new Player("lisa"),
                new CardHandInitializerStub(lisaCards));
        List<PlayerCardHand> playerHands = List.of(dompooCardHand, mayCardHand, lisaCardHand);
        final DealerCardHand dealerHand = new DealerCardHand(new CardHandInitializerStub(dealerCards));
        
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerHand, playerHands);
        
        // expected
        assertThat(blackjackJudge.getDealerLosingCount()).isEqualTo(2);
    }
    
    @Test
    void 블랙잭심판은_딜러의_무승부_갯수를_확인할_수_있다() {
        // given
        List<Card> dompooCards = List.of(HEART_10, HEART_6);
        List<Card> mayCards = List.of(HEART_2, HEART_3);
        List<Card> lisaCards = List.of(DIAMOND_1, HEART_8);
        List<Card> dealerCards = List.of(DIAMOND_1, HEART_5);
        
        final PlayerCardHand dompooCardHand = new PlayerCardHand(new Player("dompoo"), new CardHandInitializerStub(dompooCards));
        final PlayerCardHand mayCardHand = new PlayerCardHand(new Player("may"), new CardHandInitializerStub(mayCards));
        final PlayerCardHand lisaCardHand = new PlayerCardHand(new Player("lisa"),
                new CardHandInitializerStub(lisaCards));
        List<PlayerCardHand> playerHands = List.of(dompooCardHand, mayCardHand, lisaCardHand);
        final DealerCardHand dealerHand = new DealerCardHand(new CardHandInitializerStub(dealerCards));
        
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerHand, playerHands);
        
        // expected
        assertThat(blackjackJudge.getDealerDrawingCount()).isEqualTo(1);
    }
    
    @Test
    void 한_플레이어의_승패여부를_확인할_때_존재하지_않는_플레이어라면_예외가_발생한다() {
        // given
        List<Card> dompooCards = List.of(HEART_10, HEART_6);
        List<Card> mayCards = List.of(HEART_2, HEART_3);
        List<Card> dealerCards = List.of(DIAMOND_1, HEART_5);
        
        final PlayerCardHand dompooCardHand = new PlayerCardHand(new Player("dompoo"), new CardHandInitializerStub(dompooCards));
        final PlayerCardHand mayCardHand = new PlayerCardHand(new Player("may"), new CardHandInitializerStub(mayCards));
        List<PlayerCardHand> playerHands = List.of(dompooCardHand, mayCardHand);
        final DealerCardHand dealerHand = new DealerCardHand(new CardHandInitializerStub(dealerCards));
        
        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerHand, playerHands);
        
        final PlayerCardHand lisaCardHand = new PlayerCardHand(new Player("lisa"), new CardHandInitializerDummy());
        // expected
        assertThatThrownBy(() -> blackjackJudge.getWinningStatusOf(lisaCardHand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 플레이어 손패입니다.");
    }
    
    @ParameterizedTest
    @MethodSource("provideCardsAndWinningStatus")
    void 블랙잭심판은_한_플레이어의_승패_여부를_확인할_수_있다(List<Card> cards, WinningStatus winningStatus) {
        // given
        List<Card> dealerCards = List.of(DIAMOND_1, HEART_5);

        final PlayerCardHand dompooCardHand = new PlayerCardHand(new Player("dompoo"), new CardHandInitializerStub(cards));
        List<PlayerCardHand> playerHands = List.of(dompooCardHand);
        final DealerCardHand dealerHand = new DealerCardHand(new CardHandInitializerStub(dealerCards));

        final BlackjackJudge blackjackJudge = new BlackjackJudge(dealerHand, playerHands);

        // expected
        assertThat(blackjackJudge.getWinningStatusOf(dompooCardHand)).isEqualTo(winningStatus);
    }
    
    private static Stream<Arguments> provideCardsAndWinningStatus() {
        return Stream.of(
                Arguments.of(List.of(DIAMOND_10, DIAMOND_9), WinningStatus.승리),
                Arguments.of(List.of(DIAMOND_10, HEART_6), WinningStatus.무승부),
                Arguments.of(List.of(DIAMOND_10, HEART_5), WinningStatus.패배)
        );
    }
}
