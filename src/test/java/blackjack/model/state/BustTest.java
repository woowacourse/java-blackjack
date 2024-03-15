package blackjack.model.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.cards.Cards;
import blackjack.vo.Money;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BustTest {
    private Bust bust;

    @BeforeEach
    void setUp() {
        Cards bustCards = new Cards(
                List.of(new Card(CardNumber.TEN, CardShape.SPADE),
                        new Card(CardNumber.JACK, CardShape.HEART),
                        new Card(CardNumber.THREE, CardShape.CLOVER))
        );
        bust = new Bust(bustCards);
    }

    @DisplayName("bust 상태일 때 추가 카드를 뽑을 수 없다")
    @Test
    void draw() {
        Card card = new Card(CardNumber.THREE, CardShape.CLOVER);

        assertThatThrownBy(() -> bust.draw(card))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("bust 상태에서는 카드 여러장을 뽑을 수 없다")
    @Test
    void drawCards() {
        List<Card> cardsToAdd = List.of(new Card(CardNumber.TWO, CardShape.SPADE),
                new Card(CardNumber.THREE, CardShape.HEART));

        assertThatThrownBy(() -> bust.drawCards(cardsToAdd))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("bust 상태에서 카드뽑기를 멈출 수 없다")
    @Test
    void bust() {
        assertThatThrownBy(() -> bust.stand())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("bust 상태가 되면 베팅 금액을 잃는다")
    @Test
    void calculateProfit() {
        Money betAmount = new Money(1000);

        assertThat(bust.calculateProfit(betAmount)).isEqualTo(new Money(-1000));
    }
}
