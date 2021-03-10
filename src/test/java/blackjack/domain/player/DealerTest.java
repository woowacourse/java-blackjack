package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러의 점수가 16점을 초과할 때 카드를 드로우할 수 없다")
    @Test
    void 카드_추가_테스트() {
        // given, when
        Cards cards = Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.SEVEN, Shape.CLUBS)
        );
        Dealer dealer = new Dealer(cards);

        // then
        assertThatThrownBy(() -> {
            dealer.draw(Card.of(Denomination.JACK, Shape.CLUBS));
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 16점 이상이라 드로우할 수 없습니다.");
    }
}