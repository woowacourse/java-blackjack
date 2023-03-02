package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.user.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @DisplayName("카드 점수가 17보다 작으면 카드를 받아야 한다")
    @Test
    void hit_WhenScoreUnder21() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.SIX, Suits.HEART));
        dealer.hit(new Card(Denomination.JACK, Suits.HEART));
        assertThat(dealer.isHittable()).isTrue();
    }

    @DisplayName("카드 점수가 17 이상이면 카드를 받을 수 없다")
    @Test
    void stay_WhenScoreOver21() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(Denomination.SEVEN, Suits.HEART));
        dealer.hit(new Card(Denomination.JACK, Suits.HEART));
        assertThat(dealer.isHittable()).isFalse();
    }
}
