package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.Card;
import blackjack.model.CardGenerator;
import blackjack.model.CardNumber;
import blackjack.model.CardShape;
import blackjack.model.Cards;
import blackjack.model.Dealer;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @DisplayName("현재 가지고 있는 점수가 기준점수보다 작으면 카드를 추가발급받는다")
    @Test
    void addCardLessThan() {
        List<Card> given = List.of(
                new Card(CardNumber.SIX, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Cards cards = new Cards();
        cards.addCard(given);
        CardGenerator cardGenerator = new CardGenerator(maxRange -> 3);
        Dealer dealer = new Dealer(cards);

        dealer.addCard(cardGenerator);

        assertThat(dealer.getCards().getCards()).hasSize(3);
    }

    @DisplayName("현재 가지고 있는 점수가 기준점수보다 크면 카드를 발급받지 않는다")
    @Test
    void addCardGreaterThanStandard() {
        List<Card> given = List.of(
                new Card(CardNumber.SEVEN, CardShape.HEART),
                new Card(CardNumber.TEN, CardShape.CLOVER)
        );
        Cards cards = new Cards();
        cards.addCard(given);
        CardGenerator cardGenerator = new CardGenerator(maxRange -> 3);
        Dealer dealer = new Dealer(cards);

        dealer.addCard(cardGenerator);

        assertThat(dealer.getCards().getCards()).hasSize(2);
    }
}
