package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("Dealer 클래스는 딜러라는 이름을 가지고 정상적으로 생성된다.")
    void create_dealer() {
        String name = "딜러";
        Dealer dealer = new Dealer();

        assertThat(dealer.getName().get()).isEqualTo(name);
    }

    @Test
    @DisplayName("딜러는 isDealer가 참을 반환한다.")
    void check_dealer() {
        Player dealer = new Dealer();

        assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 총합이 16이하면 isValidRange가 참을 반환한다.")
    void validate_range_true() {
        Player dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.SIX, Type.CLOVER));
        dealer.hit(Card.of(CardNumber.TEN, Type.SPADE));

        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러는 카드의 총합이 16초과면 isValidRange가 거짓을 반환한다.")
    void validate_range_false() {
        Player dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.SEVEN, Type.CLOVER));
        dealer.hit(Card.of(CardNumber.TEN, Type.SPADE));

        assertThat(dealer.canHit()).isFalse();
    }
}
