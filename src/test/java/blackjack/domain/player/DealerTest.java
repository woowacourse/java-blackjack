package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("딜러 테스트")
class DealerTest {

    private static final Card ACE = new Card(CardNumber.ACE, CardShape.CLOVER);

    @Test
    @DisplayName("딜러는 한장의 카드만 공개한다")
    void dealerRevealsOnlyOneCard() {
        Dealer dealer = new Dealer();

        Cards cards = new Cards(List.of(ACE));
        dealer.addCards(cards);

        List<Card> result = dealer.getOpenedCards();

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러의 카드가 없는 경우 빈 리스트를 반환한다")
    void returnsEmptyListWhenDealerHasNoCards() {
        Dealer dealer = new Dealer();
        List<Card> result = dealer.getOpenedCards();

        assertThat(result).isEmpty();
    }
}
