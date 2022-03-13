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
    @DisplayName("isDealer 메서드는 자신이 딜러면 참을 반환한다.")
    void check_dealer() {
        Player dealer = new Dealer();

        assertThat(dealer.isDealer()).isTrue();
    }

    @Test
    @DisplayName("isValidRange 메서드는 카드의 총합이 17이상인지 검사한다.")
    void validate_range() {
        Player dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.SEVEN, Type.CLOVER));
        dealer.hit(Card.of(CardNumber.TEN, Type.SPADE));

        assertThat(dealer.isValidRange()).isFalse();
    }
}
