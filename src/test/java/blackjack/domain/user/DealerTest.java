package blackjack.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("Dealer 클래스는 딜러라는 이름을 가지고 정상적으로 생성된다.")
    void create_dealer() {
        User dealer = new Dealer();

        assertThat(dealer.getName().get()).isEqualTo("딜러");
    }

    @Test
    @DisplayName("isValidRange 메서드는 카드의 총합이 16 이하면 true를 반환한다.")
    void validate_range_true() {
        User dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.SIX, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.TEN, CardType.SPADE));

        assertThat(dealer.isValidRange()).isTrue();
    }

    @Test
    @DisplayName("isValidRange 메서드는 카드의 총합이 17 이상이면 false를 반환한다.")
    void validate_range_false() {
        User dealer = new Dealer();
        dealer.hit(Card.of(CardNumber.SEVEN, CardType.CLOVER));
        dealer.hit(Card.of(CardNumber.TEN, CardType.SPADE));

        assertThat(dealer.isValidRange()).isFalse();
    }
}
