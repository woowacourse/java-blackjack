package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StandTest {

    @Test
    void draw_throwsException() {
        // given
        Stand standState = new Stand(new CardHand());

        // when & then
        assertThatThrownBy(() -> standState.draw(new Card(CardSuit.HEART, CardRank.ACE)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임이 종료된 카드는 카드를 발급받을 수 없습니다.");
    }

    @Test
    void stand_throwsException() {
        // given
        Stand standState = new Stand(new CardHand());

        // when & then
        assertThatThrownBy(standState::stand)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임이 종료된 카드는 stand할 수 없습니다.");
    }

    @Test
    void drawInitialCards_throwsException() {
        // given
        Stand standState = new Stand(new CardHand());

        // when & then
        assertThatThrownBy(() -> standState.drawInitialCards(
                new Card(CardSuit.CLUB, CardRank.FIVE),
                new Card(CardSuit.HEART, CardRank.SEVEN)
        )).isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임 시작시에만 카드를 초기화할 수 있습니다.");
    }

    @Test
    void determineResult_returnsWin_whenOpponentIsBust() {
        // given
        CardHand myHand = new CardHand();
        myHand.add(new Card(CardSuit.SPADE, CardRank.TEN));
        myHand.add(new Card(CardSuit.CLUB, CardRank.EIGHT));
        Stand myState = new Stand(myHand);

        CardHand otherHand = new CardHand();
        otherHand.add(new Card(CardSuit.HEART, CardRank.TEN));
        otherHand.add(new Card(CardSuit.DIAMOND, CardRank.NINE));
        otherHand.add(new Card(CardSuit.CLUB, CardRank.FOUR)); // 23점 (Bust)
        Bust opponentState = new Bust(otherHand);

        // when
        GameResult result = myState.determineResult(opponentState);

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    void determineResult_returnsWin_whenMyScoreIsHigher() {
        // given
        CardHand myHand = new CardHand();
        myHand.add(new Card(CardSuit.SPADE, CardRank.TEN));
        myHand.add(new Card(CardSuit.CLUB, CardRank.EIGHT)); // 18점
        Stand myState = new Stand(myHand);

        CardHand otherHand = new CardHand();
        otherHand.add(new Card(CardSuit.HEART, CardRank.TEN));
        otherHand.add(new Card(CardSuit.DIAMOND, CardRank.SEVEN)); // 17점
        Stand opponentState = new Stand(otherHand);

        // when
        GameResult result = myState.determineResult(opponentState);

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    void determineResult_returnsLose_whenMyScoreIsLower() {
        // given
        CardHand myHand = new CardHand();
        myHand.add(new Card(CardSuit.SPADE, CardRank.TEN));
        myHand.add(new Card(CardSuit.CLUB, CardRank.SIX)); // 16점
        Stand myState = new Stand(myHand);

        CardHand otherHand = new CardHand();
        otherHand.add(new Card(CardSuit.HEART, CardRank.TEN));
        otherHand.add(new Card(CardSuit.DIAMOND, CardRank.EIGHT)); // 18점
        Stand opponentState = new Stand(otherHand);

        // when
        GameResult result = myState.determineResult(opponentState);

        // then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    void determineResult_returnsDraw_whenScoresAreEqual() {
        // given
        CardHand myHand = new CardHand();
        myHand.add(new Card(CardSuit.SPADE, CardRank.TEN));
        myHand.add(new Card(CardSuit.CLUB, CardRank.SEVEN)); // 17점
        Stand myState = new Stand(myHand);

        CardHand otherHand = new CardHand();
        otherHand.add(new Card(CardSuit.HEART, CardRank.TEN));
        otherHand.add(new Card(CardSuit.DIAMOND, CardRank.SEVEN)); // 17점
        Stand otherState = new Stand(otherHand);

        // when
        GameResult result = myState.determineResult(otherState);

        // then
        assertThat(result).isEqualTo(GameResult.DRAW);
    }

    @Test
    void determineResult_returnsLose_whenOpponentIsBlackjack() {
        // given
        CardHand myHand = new CardHand();
        myHand.add(new Card(CardSuit.SPADE, CardRank.TEN));
        myHand.add(new Card(CardSuit.CLUB, CardRank.NINE)); // 19점
        Stand myState = new Stand(myHand);

        CardHand blackjackHand = new CardHand();
        blackjackHand.add(new Card(CardSuit.HEART, CardRank.ACE));
        blackjackHand.add(new Card(CardSuit.DIAMOND, CardRank.TEN)); // 블랙잭
        Blackjack otherState = new Blackjack(blackjackHand);

        // when
        GameResult result = myState.determineResult(otherState);

        // then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }
}