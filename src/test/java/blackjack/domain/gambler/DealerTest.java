package blackjack.domain.gambler;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
    @DisplayName("처음 카드를 받은 후 딜러는 하나의 카드만 오픈한다")
    @Test
    void getInitialCardsTest() {
        // given
        Dealer dealer = new Dealer(new Name("딜러"));
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.EIGHT);
        dealer.addCard(card1);
        dealer.addCard(card2);

        // when
        List<Card> result = dealer.getInitialCards();

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.getFirst()).isEqualTo(card1);
    }
}
