import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @DisplayName("딜러의 점수가 16이하이면 카드를 받는다.")
    @Test
    void shouldHit() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Shape.HEART, Rank.KING));

        Assertions.assertThat(dealer.shouldHit()).isTrue();
    }
}
