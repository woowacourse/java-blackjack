package blackjack.domain.human;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.card.Card;
import blackjack.domain.cards.card.denomination.Denomination;
import blackjack.domain.cards.card.denomination.Suit;
import blackjack.domain.participant.human.Dealer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러 카드 추가/포인트 획득 기능 테스트")
    public void addCardToPlayerTest() {
        // given
        Card card = Card.of(Denomination.fromInitial("5"), Suit.SPADE);
        Card card2 = Card.of(Denomination.fromInitial("6"), Suit.SPADE);
        Dealer dealer = new Dealer(List.of(card, card2));

        // then
        assertThat(dealer.getPoint().hashCode()).isEqualTo(11);
    }

    @Test
    @DisplayName("딜러 히트가능 확인하는 기능 검")
    public void isAbleToHitTest() {
        // given
        Card card = Card.of(Denomination.fromInitial("5"), Suit.SPADE);
        Card card2 = Card.of(Denomination.fromInitial("6"), Suit.SPADE);
        Dealer dealer = new Dealer(List.of(card, card2));

        // then
        assertThat(dealer.isAbleToHit())
                .isTrue();
    }
}
