package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Number;
import blackjack.domain.card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    @Test
    @DisplayName("딜러가 소지한 카드가 16 이하면 true를 반환한다.")
    void checkScoreWhenUnder16Test() {
        Cards cards = new Cards(List.of(new Card(Shape.DIAMOND, Number.TEN), new Card(Shape.DIAMOND, Number.FOUR)));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isHit()).isEqualTo(true);
    }

    @Test
    @DisplayName("딜러가 소지한 카드가 16 초과면 false를 반환한다.")
    void checkScoreWhenOver16Test() {
        Cards cards = new Cards(List.of(new Card(Shape.DIAMOND, Number.TEN), new Card(Shape.DIAMOND, Number.JACK)));
        Dealer dealer = new Dealer(cards);
        assertThat(dealer.isHit()).isEqualTo(false);
    }

}
