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
        blackjackGame.saveParticipants("영기,라이");
        blackjackGame.setDeck(new Deck(List.of(
                new Card(Suit.SPADE, Rank.KING),
                new Card(Suit.HEART, Rank.TWO),
                new Card(Suit.DIAMOND, Rank.FIVE),
                new Card(Suit.SPADE, Rank.ACE),
                new Card(Suit.HEART, Rank.THREE),
                new Card(Suit.DIAMOND, Rank.SIX),
                new Card(Suit.CLUB, Rank.TEN),
                new Card(Suit.CLUB, Rank.SEVEN)
        )));
        blackjackGame.dealCards();
    }

    @Test
    void 초기_배분_후_영기_카드_확인() {
        // when
        List<Card> cards = blackjackGame.getUsers().get(0).getCards();

        // then
        assertThat(cards.get(0).getRankName()).isEqualTo("K");
        assertThat(cards.get(0).getSuitName()).isEqualTo("스페이드");
        assertThat(cards.get(1).getRankName()).isEqualTo("A");
    }

    @Test
    void 초기_배분_후_라이_카드_확인() {
        // when
        List<Card> cards = blackjackGame.getUsers().get(1).getCards();

        // then
        assertThat(cards.get(0).getRankName()).isEqualTo("2");
        assertThat(cards.get(0).getSuitName()).isEqualTo("하트");
    }

    @Test
    void 라이_히트_후_카드_추가됨() {
        // when
        blackjackGame.processPlayerDecision(1);

        // then
        assertThat(blackjackGame.getUsers().get(1).getCards()).hasSize(3);
    }

    @Test
    void 딜러_점수_16이하_히트_발생() {
        // when
        boolean dealt = blackjackGame.dealToDealer();

        // then
        assertThat(dealt).isTrue();
    }

    @Test
    void 영기_블랙잭으로_승리() {
        // given
        blackjackGame.dealToDealer();

        // when & then
        assertThat(blackjackGame.getUserResults().get("영기")).isEqualTo(GameResult.WIN);
    }

    @Test
    void 라이_히트_후_딜러보다_낮아_패배() {
        // given
        blackjackGame.processPlayerDecision(1);
        blackjackGame.dealToDealer();

        // when & then
        assertThat(blackjackGame.getUserResults().get("라이")).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 딜러_결과에_승패_포함() {
        // given
        blackjackGame.dealToDealer();

        // when
        var dealerResults = blackjackGame.getDealerResults();

        // then
        assertThat(dealerResults).containsKey(GameResult.WIN);
        assertThat(dealerResults).containsKey(GameResult.LOSE);
    }
}