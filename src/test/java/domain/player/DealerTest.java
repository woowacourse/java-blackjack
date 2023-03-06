package domain.player;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void beforeEach() {
        //given
        dealer = Dealer.create();
        dealer.takeCard(Card.of(Suit.DIAMOND, Rank.FIVE));
        dealer.takeCard(Card.of(Suit.SPADE, Rank.TWO));
        dealer.takeCard(Card.of(Suit.CLUBS, Rank.SIX));
    }


    @Test
    @DisplayName("가지고 있는 카드 중 첫번째를 반환한다.")
    void giveCards_theReturnFirstCard() {
        //when
        final Card firstCard = dealer.getFirstCard();

        //then
        assertThat(firstCard).isEqualTo(Card.of(Suit.DIAMOND, Rank.FIVE));
    }

    @Nested
    class IsHitTest {

        @Test
        @DisplayName("카드 점수의 총합이 16 이하면 true를 리턴한다")
        void givenHitNumber_thenReturnTrue() {
            //when
            final boolean dealerHit = dealer.isHit();

            //then
            assertThat(dealerHit).isTrue();
        }

        @Test
        @DisplayName("카드 점수의 총합이 17 초과면 false를 리턴한다")
        void givenStayNumber_thenReturnFalse() {
            //given
            dealer.takeCard(Card.of(Suit.DIAMOND, Rank.FOUR));

            //when
            final boolean dealerHit = dealer.isHit();

            //then
            assertThat(dealerHit).isFalse();
        }
    }

}
