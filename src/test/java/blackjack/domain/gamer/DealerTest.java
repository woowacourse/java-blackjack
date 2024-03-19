package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Number;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러가 보유한 카드의 합이 16 이하이면 true를 리턴한다.")
    @Test
    void returnTrueIfDealerHandSumIsEqualOrLessThanHitThreshold() {
        final Dealer dealer = new Dealer();
        dealer.draw(new Card(Number.TEN, Suit.DIAMOND));
        dealer.draw(new Card(Number.SIX, Suit.DIAMOND));

        boolean actual = dealer.canHit();

        assertThat(actual).isEqualTo(true);
    }

    @DisplayName("딜러가 보유한 카드의 합이 16 초과이면 false를 리턴한다.")
    @Test
    void returnFalseDealerHandSumIsGreaterThanHitThreshold() {
        final Dealer dealer = new Dealer();
        dealer.draw(new Card(Number.TEN, Suit.DIAMOND));
        dealer.draw(new Card(Number.SEVEN, Suit.DIAMOND));

        boolean actual = dealer.canHit();

        assertThat(actual).isEqualTo(false);
    }

    @DisplayName("딜러의 페이스 업 카드를 리턴한다.")
    @Test
    void returnFaceUpCard() {
        final Dealer dealer = new Dealer();
        dealer.draw(new Card(Number.TEN, Suit.DIAMOND));
        dealer.draw(new Card(Number.SEVEN, Suit.DIAMOND));

        Card faceUpCard = dealer.findFaceUpCard();

        assertThat(faceUpCard).isEqualTo(new Card(Number.TEN, Suit.DIAMOND));
    }
}
