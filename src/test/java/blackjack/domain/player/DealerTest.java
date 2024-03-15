package blackjack.domain.player;

import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardShape.HEART;
import static blackjack.fixture.PlayerFixture.dealer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @Test
    void 딜러는_핸드의_숫자_합계가_16_이하이면_추가_카드를_필요로한다() {
        // given
        Dealer dealer = dealer(new Card(KING, HEART));

        // when
        boolean moreCardNeeded = dealer.isMoreCardNeeded();

        // then
        assertThat(moreCardNeeded).isTrue();
    }

    @Test
    void 딜러의_핸드가_비어있을_때_첫번째_카드를_요청하면_예외가_발생한다() {
        // given
        Dealer dealer = dealer();

        // when & then
        assertThatCode(dealer::getFirstCard)
                .isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    void 딜러가_핸드에_카드를_가지고_있을_때_첫번째_카드를_요청하면_정상적으로_반환한다() {
        // given
        Card card = new Card(KING, HEART);
        Dealer dealer = dealer(card);

        // when
        Card firstCard = dealer.getFirstCard();

        // then
        assertThat(card).isEqualTo(firstCard);
    }
}
