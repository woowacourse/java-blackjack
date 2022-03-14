package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 카드 추가 테스트")
    public void dealerAddCardTest() {
        Dealer dealer = Dealer.of();

        Card card5 = Card.of(Denomination.of("5"), Symbol.of("스페이드"));
        Card card6 = Card.of(Denomination.of("6"), Symbol.of("하트"));

        dealer.addCard(card5);
        dealer.addCard(card6);

        assertThat(dealer.getPoint())
                .isEqualTo(11);
    }


}
