package domain.user;

import static domain.card.Denomination.JACK;
import static domain.card.Denomination.SEVEN;
import static domain.card.Denomination.SIX;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suits;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setDealer() {
        dealer = new Dealer();
    }

    @DisplayName("카드 점수가 17보다 작으면 카드를 받아야 한다")
    @Test
    void hit_WhenScoreUnder21() {
        addCards(List.of(SIX, JACK));

        assertThat(dealer.isHittable()).isTrue();
    }

    @DisplayName("카드 점수가 17 이상이면 카드를 받을 수 없다")
    @Test
    void stay_WhenScoreOver21() {
        addCards(List.of(SEVEN, JACK));

        assertThat(dealer.isHittable()).isFalse();
    }

    @DisplayName("플레이 중간에는 딜러의 제일 첫 번째 카드만 보여준다")
    @Test
    void getCardWithHidden() {
        addCards(List.of(SIX, JACK));
        assertThat(dealer.getFirstCard().getDenomination()).isEqualTo(SIX);
    }

    private void addCards(List<Denomination> denominations) {
        for (Denomination denomination : denominations) {
            dealer.hit(Card.of(denomination, Suits.HEART));
        }
    }
}
