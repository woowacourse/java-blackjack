package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BlackjackTest {

    @Test
    void draw_throwsException() {
        // given
        Blackjack blackjackState = new Blackjack(new CardHand());

        // when & then
        assertThatThrownBy(() -> blackjackState.draw(new Card(CardSuit.HEART, CardRank.ACE)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임이 종료된 카드는 카드를 발급받을 수 없습니다.");
    }

    @Test
    void stand_throwsException() {
        // given
        Blackjack blackjackState = new Blackjack(new CardHand());

        // when & then
        assertThatThrownBy(blackjackState::stand)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임이 종료된 카드는 stand할 수 없습니다.");
    }

    @Test
    void drawInitialCards_throwsException() {
        // given
        Blackjack blackjackState = new Blackjack(new CardHand());

        // when & then
        assertThatThrownBy(() -> blackjackState.drawInitialCards(
                new Card(CardSuit.CLUB, CardRank.FIVE),
                new Card(CardSuit.HEART, CardRank.SEVEN)
        )).isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임 시작시에만 카드를 초기화할 수 있습니다.");
    }

    @Test
    void determineResult_returnsDraw_whenOpponentIsBlackjack() {
        // given
        CardHand blackjackHand = new CardHand();
        blackjackHand.add(new Card(CardSuit.HEART, CardRank.ACE));
        blackjackHand.add(new Card(CardSuit.DIAMOND, CardRank.TEN)); // 블랙잭

        Blackjack myBlackjackState = new Blackjack(blackjackHand);
        Blackjack opponentBlackjackState = new Blackjack(blackjackHand);

        // when
        GameResult result = myBlackjackState.determineResult(opponentBlackjackState);

        // then
        assertThat(result).isEqualTo(GameResult.DRAW);
    }

    @Test
    void determineResult_returnsBlackjackWin_whenOpponentIsNotBlackjack() {
        // given
        CardHand blackjackHand = new CardHand();
        blackjackHand.add(new Card(CardSuit.HEART, CardRank.ACE));
        blackjackHand.add(new Card(CardSuit.DIAMOND, CardRank.TEN)); // 블랙잭

        CardHand normalHand = new CardHand();
        normalHand.add(new Card(CardSuit.SPADE, CardRank.TEN));
        normalHand.add(new Card(CardSuit.CLUB, CardRank.NINE)); // 19점

        Blackjack myBlackjackState = new Blackjack(blackjackHand);
        Stand opponentStandState = new Stand(normalHand);

        // when
        GameResult result = myBlackjackState.determineResult(opponentStandState);

        // then
        assertThat(result).isEqualTo(GameResult.BLACKJACK_WIN);
    }

    @Test
    void isBlackjack_returnsTrue() {
        // given
        Blackjack blackjackState = new Blackjack(new CardHand());

        // when & then
        assertThat(blackjackState.isBlackjack()).isTrue();
    }
}