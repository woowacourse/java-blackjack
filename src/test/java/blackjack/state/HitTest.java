package blackjack.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class HitTest {

    @Test
    void drawInitialCards_throwsException() {
        // given
        Hit hitState = new Hit(new CardHand());

        // when & then
        assertThatThrownBy(() -> hitState.drawInitialCards(
                new Card(CardSuit.CLUB, CardRank.FIVE),
                new Card(CardSuit.HEART, CardRank.SEVEN)
        )).isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("게임 시작시에만 카드를 초기화할 수 있습니다.");
    }

    @Test
    void draw_returnsNewHitState_whenNotBust() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.SPADE, CardRank.TEN));
        Hit hitState = new Hit(cardHand);

        Card additionalCard = new Card(CardSuit.CLUB, CardRank.FIVE); // 15점 유지

        // when
        State nextState = hitState.draw(additionalCard);

        // then
        assertThat(nextState).isInstanceOf(Hit.class);
    }

    @Test
    void draw_returnsBustState_whenBust() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.HEART, CardRank.TEN));
        cardHand.add(new Card(CardSuit.DIAMOND, CardRank.NINE)); // 19점

        Hit hitState = new Hit(cardHand);
        Card additionalCard = new Card(CardSuit.CLUB, CardRank.FIVE); // 24점 (Bust)

        // when
        State nextState = hitState.draw(additionalCard);

        // then
        assertThat(nextState).isInstanceOf(Bust.class);
    }

    @Test
    void stand_returnsStandState() {
        // given
        CardHand cardHand = new CardHand();
        cardHand.add(new Card(CardSuit.SPADE, CardRank.TEN));
        cardHand.add(new Card(CardSuit.HEART, CardRank.SEVEN)); // 17점

        Hit hitState = new Hit(cardHand);

        // when
        State nextState = hitState.stand();

        // then
        assertThat(nextState).isInstanceOf(Stand.class);
    }

    @Test
    void isFinished_false() {
        // given
        Hit hitState = new Hit(new CardHand());

        // when & then
        assertThat(hitState.isFinished()).isFalse();
    }
}