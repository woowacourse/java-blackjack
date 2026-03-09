package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vo.GameResult;
import vo.Rank;
import vo.Suit;

public class BlackjackGameFlowTest {

    // 카드 배분 순서 (dealCards 2라운드):
    // round1: 영기 → 라이 → 딜러
    // round2: 영기 → 라이 → 딜러
    // 영기: KING(10) + ACE(11) = 21 블랙잭
    // 라이: TWO(2) + THREE(3) = 5, 히트 → TEN(10) = 15
    // 딜러: FIVE(5) + SIX(6) = 11 → 히트 → SEVEN(7) = 18
    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        blackjackGame = new BlackjackGame();
        blackjackGame.saveParticipants(List.of("영기", "라이"));
        blackjackGame.setDeck(new Deck(List.of(
                new Card(Suit.SPADE, Rank.KING),    // 영기 round1
                new Card(Suit.HEART, Rank.TWO),     // 라이 round1
                new Card(Suit.DIAMOND, Rank.FIVE),  // 딜러 round1
                new Card(Suit.SPADE, Rank.ACE),     // 영기 round2
                new Card(Suit.HEART, Rank.THREE),   // 라이 round2
                new Card(Suit.DIAMOND, Rank.SIX),   // 딜러 round2
                new Card(Suit.CLUB, Rank.TEN),      // 라이 히트
                new Card(Suit.CLUB, Rank.SEVEN)     // 딜러 히트
        )));
        blackjackGame.dealCards();
    }

    @Test
    void 초기_배분_후_영기_카드_표시() {
        // when
        List<String> displays = blackjackGame.getUserCardsDisplays();

        // then
        assertThat(displays.get(0)).contains("영기", "K스페이드", "A스페이드");
    }

    @Test
    void 초기_배분_후_라이_카드_표시() {
        // when
        List<String> displays = blackjackGame.getUserCardsDisplays();

        // then
        assertThat(displays.get(1)).contains("라이", "2하트", "3하트");
    }

    @Test
    void 라이_히트_후_카드_추가됨() {
        // when
        String result = blackjackGame.processPlayerDecision(1);

        // then
        assertThat(result).contains("라이", "2하트", "3하트", "10클로버");
    }

    @Test
    void 딜러_점수_16이하_히트_발생() {
        // when
        String message = blackjackGame.determineDealToDealer();

        // then
        assertThat(message).isNotEmpty();
    }

    @Test
    void 영기_블랙잭으로_승리() {
        // given
        blackjackGame.determineDealToDealer();

        // when
        List<String> results = blackjackGame.evaluateGame();

        // then
        assertThat(results.get(1)).contains("영기", GameResult.WIN.getName());
    }

    @Test
    void 라이_히트_후_딜러보다_낮아_패배() {
        // given
        blackjackGame.processPlayerDecision(1); // 라이 히트 → 15
        blackjackGame.determineDealToDealer();  // 딜러 히트 → 18

        // when
        List<String> results = blackjackGame.evaluateGame();

        // then
        assertThat(results.get(2)).contains("라이", GameResult.LOSE.getName());
    }

    @Test
    void 딜러_최종_결과_포맷() {
        // given
        blackjackGame.determineDealToDealer();

        // when
        List<String> results = blackjackGame.evaluateGame();

        // then
        assertThat(results.get(0)).contains("딜러", "승", "패");
    }
}