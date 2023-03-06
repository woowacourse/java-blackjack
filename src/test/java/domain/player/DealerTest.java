package domain.player;

import domain.card.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class DealerTest {

    @Test
    @DisplayName("가지고 있는 카드 중 첫번째를 반환한다.")
    void giveCards_theReturnFirstCard() {
        //given
        final Dealer dealer = Dealer.create(0);

        final List<Rank> ranks = List.of(Rank.EIGHT, Rank.SIX, Rank.SEVEN);
        final Deck deck = Deck.from(TestCardGenerator.from(ranks));

        for (int i = 0; i < ranks.size(); i++) {
            dealer.takeCard(deck.dealCard());
        }

        //when
        final Card firstCard = dealer.getFirstCard();

        //then
        assertThat(firstCard).isEqualTo(Card.of(Suit.CLUBS, Rank.EIGHT));
    }

    @Nested
    class IsHitTest {

        @Test
        @DisplayName("카드 점수의 총합이 16 이하면 true를 리턴한다")
        void givenHitNumber_thenReturnTrue() {
            //given
            final Dealer dealer = Dealer.create(15);

            //when
            final boolean dealerHit = dealer.isHit();

            //then
            assertThat(dealerHit).isTrue();
        }

        @Test
        @DisplayName("카드 점수의 총합이 17 이상이면 false를 리턴한다")
        void givenStayNumber_thenReturnFalse() {
            //given
            final Dealer dealer = Dealer.create(17);

            //when
            final boolean dealerHit = dealer.isHit();

            //then
            assertThat(dealerHit).isFalse();
        }
    }

}
