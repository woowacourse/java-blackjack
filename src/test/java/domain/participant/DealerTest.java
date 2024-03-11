package domain.participant;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러의 점수가 16이하이면 카드를 받는다.")
    @Test
    void shouldHit() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.KING));
        dealer.receiveCard(new Card(Shape.HEART, Rank.SIX));

        Assertions.assertThat(dealer.shouldHit()).isTrue();
    }

    @DisplayName("딜러의 점수가 17이상이면 카드를 받을 수 없다.")
    @Test
    void ShoulNotdHit() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.KING));
        dealer.receiveCard(new Card(Shape.HEART, Rank.SEVEN));

        Assertions.assertThat(dealer.shouldHit()).isFalse();
    }
}
