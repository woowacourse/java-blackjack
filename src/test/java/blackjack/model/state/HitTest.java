package blackjack.model.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.model.cards.Card;
import blackjack.model.cards.CardNumber;
import blackjack.model.cards.CardShape;
import blackjack.model.cards.Cards;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HitTest {
    private Hit hit;

    @BeforeEach
    void setUp() {
        Cards hitCards = new Cards(
                List.of(new Card(CardNumber.FIVE, CardShape.SPADE),
                        new Card(CardNumber.TEN, CardShape.HEART))
        );
        hit = new Hit(hitCards);
    }

    @DisplayName("hit 상태일 추가 카드를 뽑아 기준점수를 넘으면 bust가 된다")
    @Test
    void drawBust() {
        Card card = new Card(CardNumber.TEN, CardShape.CLOVER);

        assertThat(hit.draw(card)).isInstanceOf(Bust.class);
    }

    @DisplayName("hit 상태일 추가 카드를 뽑아 기준점수를 넘지않으면 hit이 된다")
    @Test
    void drawHit() {
        Card card = new Card(CardNumber.FIVE, CardShape.CLOVER);

        assertThat(hit.draw(card)).isInstanceOf(Hit.class);
    }

    @DisplayName("hit 상태에서는 카드 여러장을 뽑을 수 없다")
    @Test
    void drawCards() {
        List<Card> cardsToAdd = List.of(new Card(CardNumber.TWO, CardShape.SPADE),
                new Card(CardNumber.THREE, CardShape.HEART));

        assertThatThrownBy(() -> hit.drawCards(cardsToAdd))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("hit 상태에서 카드뽑기를 멈추면 stand 상태가 된다")
    @Test
    void stand() {
        assertThat(hit.stand()).isInstanceOf(Stand.class);
    }

    @DisplayName("hit 상태에서는 승패를 계산할 수 없다")
    @Test
    void calculateProfit() {
        State state = new Stand(new Cards());

        assertThatThrownBy(() -> hit.determineResult(state))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
