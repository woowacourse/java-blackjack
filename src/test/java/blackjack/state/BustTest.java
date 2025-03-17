package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BustTest {

    @Test
    void draw_throwsException() {
        // given
        Bust bustState = new Bust(new CardHand());

        // when & then
        assertThatThrownBy(() -> bustState.draw(new Card(CardSuit.HEART, CardRank.ACE)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임이 종료된 카드는 카드를 발급받을 수 없습니다.");
    }

    @Test
    void stand_throwsException() {
        // given
        Bust bustState = new Bust(new CardHand());

        // when & then
        assertThatThrownBy(bustState::stand)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임이 종료된 카드는 stand할 수 없습니다.");
    }

    @Test
    void drawInitialCards_throwsException() {
        // given
        Bust bustState = new Bust(new CardHand());

        // when & then
        assertThatThrownBy(() -> bustState.drawInitialCards(
                new Card(CardSuit.CLUB, CardRank.FIVE),
                new Card(CardSuit.HEART, CardRank.SEVEN)
        )).isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임 시작시에만 카드를 초기화할 수 있습니다.");
    }

    @Test
    void determineResult_returnsLose_always() {
        // given
        CardHand bustHand = new CardHand();
        bustHand.add(new Card(CardSuit.HEART, CardRank.TEN));
        bustHand.add(new Card(CardSuit.DIAMOND, CardRank.NINE));
        bustHand.add(new Card(CardSuit.CLUB, CardRank.FIVE)); // 24점 (Bust)

        Bust bustState = new Bust(bustHand);

        CardHand otherHand = new CardHand();
        otherHand.add(new Card(CardSuit.SPADE, CardRank.TEN));
        otherHand.add(new Card(CardSuit.CLUB, CardRank.EIGHT)); // 18점
        Stand otherState = new Stand(otherHand);

        // when
        GameResult result = bustState.determineResult(otherState);

        // then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    void isBust_returnsTrue() {
        // given
        Bust bustState = new Bust(new CardHand());

        // when & then
        assertThat(bustState.isBust()).isTrue();
    }
}