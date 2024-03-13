package blackjack.domain.dealer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.rule.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러는 카드 점수가 16초과일 때까지 카드를 더 받는다.")
    @Test
    void hitCount() {
        // given
        final Card card = Card.of(CardNumber.TWO, CardShape.DIA);

        final Dealer dealer = new Dealer(new Deck(List.of(card, card, card, card, card, card, card, card, card, card)));

        // when
        final int hitCount = dealer.hit();

        // then
        assertThat(hitCount).isEqualTo(7);
    }

    @DisplayName("딜러는 Hit 후 16점 보다 크다.")
    @Test
    void hit() {
        // given
        final Dealer dealer = new Dealer(Deck.create());

        // when
        dealer.hit();

        // then
        assertThat(dealer.getHands().calculateScore().isBiggerThan(new Score(16))).isTrue();
    }
}
