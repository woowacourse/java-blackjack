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

class StandTest {
    private Stand stand;

    @BeforeEach
    void setUp() {
        Cards standCards = new Cards(
                List.of(new Card(CardNumber.TWO, CardShape.SPADE),
                        new Card(CardNumber.EIGHT, CardShape.HEART))
        );
        stand = new Stand(standCards);
    }

    @DisplayName("stand 상태일 때 추가 카드를 뽑을 수 없다")
    @Test
    void draw() {
        Card card = new Card(CardNumber.THREE, CardShape.CLOVER);

        assertThatThrownBy(() -> stand.draw(card))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("stand 상태에서는 카드 여러장을 뽑을 수 없다")
    @Test
    void drawCards() {
        List<Card> cardsToAdd = List.of(new Card(CardNumber.TWO, CardShape.SPADE),
                new Card(CardNumber.THREE, CardShape.HEART));

        assertThatThrownBy(() -> stand.drawCards(cardsToAdd))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("stand 상태에서 카드뽑기를 멈출 수 없다")
    @Test
    void stand() {
        assertThatThrownBy(() -> stand.stand())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("stand 상태로 승리하면 베팅금액의 1배를 받는다")
    @Test
    void calculateProfit() {
        Money betAmount = new Money(1000);
        assertThat(stand.calculateProfit(betAmount)).isEqualTo(new Money(1000));
    }

    @DisplayName("stand 상태의 카드 점수를 계산한다")
    @Test
    void getScore() {
        assertThat(stand.getScore()).isEqualTo(10);
    }
}
