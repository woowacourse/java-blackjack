package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러 점수가 16점이 초과되면 카드를 추가할 수 없다.")
    @Test
    void 카드_추가_테스트() {
        // given, when
        Cards cards = Cards.of(
            Card.of(Denomination.KING, Shape.CLUBS),
            Card.of(Denomination.SEVEN, Shape.CLUBS)
        );
        Player dealer = new Dealer("테스트 게이머", cards);

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            dealer.addCard(Card.of(Denomination.FIVE, Shape.CLUBS));
        });
    }

}