package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.element.Denomination;
import blackjack.domain.card.element.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 카드 추가/포인트 획득 기능 검사")
    public void addCardToPlayerTest() {
        // given
        Dealer dealer = Dealer.newInstance();
        Card card5 = Card.of(Denomination.stringOf("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.stringOf("6"), Suit.SPADE);

        // when
        dealer.addCard(card5);
        dealer.addCard(card6);

        // then
        assertThat(dealer.getPoint()).isEqualTo(11);
    }

    @Test
    @DisplayName("딜러 히트가능 확인하는 기능 검")
    public void isAbleToHitTest() {
        // given
        Dealer dealer = Dealer.newInstance();
        Card card5 = Card.of(Denomination.stringOf("5"), Suit.SPADE);
        Card card6 = Card.of(Denomination.stringOf("6"), Suit.SPADE);

        // when
        dealer.addCard(card5);
        dealer.addCard(card6);

        // then
        assertThat(dealer.isAbleToHit()).isTrue();
    }
}
