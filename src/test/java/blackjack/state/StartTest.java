package blackjack.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class StartTest {

    @Test
    void cantDraw_whenStateIsStart() {
        // given
        Start startState = new Start(new CardHand());

        // when & then
        assertThatThrownBy(() -> startState.draw(new Card(CardSuit.HEART, CardRank.ACE)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임 시작 전에는 카드를 뽑을 수 없습니다.");
    }

    @Test
    void cantStand_whenStateIsStart() {
        // given
        Start startState = new Start(new CardHand());

        // when & then
        assertThatThrownBy(startState::stand)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임 시작 전에는 stand할 수 없습니다.");
    }

    @Test
    void cantDetermineResult_whenStateIsStart() {
        // given
        Start startState = new Start(new CardHand());

        // when & then
        assertThatThrownBy(() -> startState.determineResult(new Hit(new CardHand())))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임 종료 전에는 승패를 계산할 수 없습니다.");
    }

    @Test
    void startWithBlackjack_and_State_is_Blackjack() {
        // given
        Start startState = new Start(new CardHand());
        Card ace = new Card(CardSuit.SPADE, CardRank.ACE);
        Card ten = new Card(CardSuit.HEART, CardRank.TEN);

        // when
        HandState nextHandState = startState.drawInitialCards(ace, ten);

        // then
        assertThat(nextHandState).isInstanceOf(Blackjack.class);
    }

    @Test
    void drawInitialCards_and_State_is_Hit() {
        // given
        Start startState = new Start(new CardHand());
        Card eight = new Card(CardSuit.CLUB, CardRank.EIGHT);
        Card five = new Card(CardSuit.DIAMOND, CardRank.FIVE);

        // when
        HandState nextHandState = startState.drawInitialCards(eight, five);

        // then
        assertThat(nextHandState).isInstanceOf(Hit.class);
    }

}