package domain.calculatestrategy;

import static org.assertj.core.api.Assertions.assertThat;

import domain.deck.Card;
import domain.deck.Rank;
import domain.deck.Shape;
import domain.gamer.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerStrategyTest {

    @DisplayName("딜러의 카드 합을 계산한다.")
    @Test
    void 딜러의_카드_합을_계산한다() {

        // given
        final Dealer dealer = new Dealer();
        dealer.hit(new Card(Rank.ACE, Shape.SPADE));
        dealer.hit(new Card(Rank.FIVE, Shape.SPADE));
        dealer.hit(new Card(Rank.ACE, Shape.HEART));

        // when
        final int sumOfRank = dealer.getSumOfRank();

        // then
        assertThat(sumOfRank).isEqualTo(17);
    }

}
