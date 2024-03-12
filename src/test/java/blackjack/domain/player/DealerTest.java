package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardSuit;
import blackjack.domain.card.Hand;
import fixture.HandFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 16 이하이면 히트가 가능하다.")
    @Test
    void testCanHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal16();

        Dealer dealer = new Dealer(hand);

        // when
        boolean actual = dealer.canHit();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("딜러는 17점 이상이면 히트가 불가능하다.")
    @Test
    void testCanNotHit() {
        // given
        Hand hand = HandFixture.createHandWithScoreTotal16();
        hand.append(new Card(CardRank.ACE, CardSuit.HEART));

        Dealer dealer = new Dealer(hand);

        // when
        boolean actual = dealer.canHit();

        // then
        assertThat(actual).isFalse();
    }
}
