package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GamerTest {

    @DisplayName("게이머의 점수가 21점이 초과되면 카드를 추가할 수 없다.")
    @Test
    void 카드_추가_테스트() {
        // given, when
        Cards cards = Cards.of(Arrays.asList(
            Card.of(Denomination.KING, Shape.CLUBS),
            Card.of(Denomination.KING, Shape.CLUBS),
            Card.of(Denomination.ACE, Shape.CLUBS)
        ));
        Player gamer = new Gamer("테스트 게이머", cards);

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            gamer.addCard(Card.of(Denomination.FIVE, Shape.CLUBS));
        });
    }
}
